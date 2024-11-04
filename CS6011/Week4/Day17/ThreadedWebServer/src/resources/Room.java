package resources;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Room {
//    private ArrayList<String> messages = new ArrayList<>();
    static HashMap<String, ArrayList<String>> roomAndMessages = new HashMap<>();
    static HashMap<String, ArrayList<DataOutputStream>> roomsAndClients = new HashMap<>();


    //getRoom checks if room already exists, if so return it, else create new and return that
    public static synchronized void getRoom(DataOutputStream out, String roomName, String userName) throws IOException {
        String message = userName + " joined " + roomName;
        if(roomsAndClients.containsKey(roomName)){
            //add client to existing room
            roomsAndClients.get(roomName).add(out);
            roomAndMessages.get(roomName).add(userName + " : "+ message);
        } else {
            //create new room and clients list
            System.out.println("Creating new room "+roomName);
            ArrayList<DataOutputStream> clients = new ArrayList<>();
            clients.add(out);
            roomsAndClients.put(roomName, clients);
            ArrayList<String> messages = new ArrayList<>();
            messages.add(userName + " : "+ message);
            roomAndMessages.put(roomName, messages);
            roomAndMessages.keySet().forEach(key -> {System.out.println(key);});
//            roomAndMessages.get(roomName).add(userName + " : "+ message);
        }
        //let client know user joined room successfully
        sendOutput(out, "join", roomName, userName, "");

        //send message history of room to new user
        for(String roomMessage : roomAndMessages.get(roomName)){
            System.out.println("Getting msg history for room: "+ roomName);
            //deconstruct message string to get user name and msg
            String[] messages = roomMessage.split(" : ");
            String msgUserName = messages[0];
            String msgContent = messages[1];
            System.out.println(msgContent);
            sendOutput(out, "message", roomName, msgUserName, msgContent);
        }

        //transmit user joined message to all clients
            for(DataOutputStream output: roomsAndClients.get(roomName)){
                //send joined message to all clients except user as they would have got it along with message history
                if(output != out){
                    try {
                        sendOutput(output, "message", roomName, userName, userName+" joined "+roomName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    break;
                }

            }

    }


    private static void sendOutput(DataOutputStream out, String messageType, String roomName, String userName, String message) throws IOException {

        // FIN and text opcode
        out.writeByte(0x81);
        String outputMsg = "";
        if(messageType.equals("join")){
            outputMsg = "{\"type\":\""+ messageType +"\"}";
        }
        else if(messageType.equals("message")){
            outputMsg = "{\"type\":\""+messageType+"\",\"user\":\""+userName+"\",\"message\":\""+message+"\"}";
        }
        else if(messageType.equals("rooms")){
            outputMsg = "{\"type\":\""+messageType+"\",\"rooms\":\""+message+"\"}";
        }

        long jsonMsgLength = outputMsg.getBytes().length;

        if(jsonMsgLength == 127){
            out.writeByte(0x7F);
            out.writeLong(jsonMsgLength);
        } else if(jsonMsgLength == 126){
            out.writeByte(0x7D);
            out.writeShort((int)jsonMsgLength);
        } else {
            out.writeByte((int)jsonMsgLength);
        }
        System.out.println(Arrays.toString(outputMsg.getBytes()));
        out.write(outputMsg.getBytes());
        out.flush();
    }

    public static void transmitMessage(DataOutputStream out, String roomName, String userName, String clientMessage) throws IOException {
        System.out.println();
        //add messsge to room messages
        String updatedClientMessage = userName + " : " + clientMessage;
        roomAndMessages.get(roomName).add(updatedClientMessage);
        //find all client outstreams for room and transmit message
        for(DataOutputStream output: roomsAndClients.get(roomName)){
            sendOutput(output, "message", roomName, userName, clientMessage);
        }
    }

    public static void leaveRoom(DataOutputStream out, String roomName, String userName) throws IOException {
        //remove client outstream from roomsAndClients
        roomsAndClients.get(roomName).remove(out);
        System.out.println("deleted out? "+roomsAndClients.get(roomName).contains(out));
        //add user left room msg to roomsAndMessages
        roomAndMessages.get(roomName).add(userName + " : " + userName +" has left room "+roomName);
        //if outstream array is not empty for room, send user left room message to all other clients in room
        if(!roomsAndClients.get(roomName).isEmpty()){
            for(DataOutputStream output : roomsAndClients.get(roomName)){
                sendOutput(output, "message", roomName, userName, userName +" has left room "+roomName);
            }
        }
    }

    public static void getRooms(DataOutputStream out) throws IOException {
        StringBuilder roomsMsg = new StringBuilder("");

        Iterator<String> iterator = roomsAndClients.keySet().iterator();
        while(iterator.hasNext()){
            roomsMsg.append(iterator.next());
            if(iterator.hasNext()){
                roomsMsg.append(",");
            }
        }
//        roomsMsg.append("]");

        sendOutput(out, "rooms", "", "", roomsMsg.toString());

    }
    //add a client synchronized
    //send message to all clients in the room
    //remove a client


}

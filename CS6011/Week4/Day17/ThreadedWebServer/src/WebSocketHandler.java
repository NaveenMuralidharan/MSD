import resources.Room;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class WebSocketHandler {
    InputStream inputStream;
    OutputStream outputStream;
    String webSocketKey;
    String userName;
    String roomName;

    public WebSocketHandler(InputStream inputStream, OutputStream outputStream, String webSocketKey){
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.webSocketKey = webSocketKey;
    }

    public void startConnection() throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        String secret = Base64.getEncoder().encodeToString( md.digest( ( webSocketKey +
                "258EAFA5-E914-47DA-95CA-C5AB0DC85B11" ).getBytes() ) );

        String response = "HTTP/1.1 101 Switching Protocols\r\nUpgrade: websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Accept: "+secret+"\r\n\r\n";
        outputStream.write(response.getBytes());
        while(true){
//            System.out.println("Keep running");
            DataInputStream in = new DataInputStream(inputStream);
            byte[] header = in.readNBytes(2);
            //get FIN
            boolean finished = (header[0] & 0x80) >0;
            //get opcode
            int opcode = header[0] & 0x0F;
            //if connection closed, throw exception
            if(opcode == 0x8){
                throw new Exception("connection closed");
            }
            //check if masked
            boolean masked = (header[1] & 0x80) !=0;
            //get payload length
            long len = header[1] & 0x7F;
            if(len == 126){
                len = in.readUnsignedShort();
            } else if(len == 127){
                len = in.readLong();
            }
            byte[] mask = in.readNBytes(4);
            byte[] message = in.readNBytes((int)len);

            for(int i=0; i<len; i++){
                message[i] ^= mask[i%4];
            }
            String msgReceived = new String(message);

            System.out.println(new String(message));

            //extract messageType, userName and roomName
            DataOutputStream out = new DataOutputStream(outputStream);
            String messageType="";
            String[] receivedMessages = msgReceived.split(",");
            if(receivedMessages[0].equals("join")){
                messageType =  receivedMessages[0];
                userName =  receivedMessages[1];
                roomName =  receivedMessages[2];
                Room.getRoom(out, roomName, userName);
            } else if(receivedMessages[0].equals("message")) {
                messageType =  receivedMessages[0];
                String clientMessage = receivedMessages[1];
                Room.transmitMessage(out, roomName, userName, clientMessage);
            } else if(receivedMessages[0].equals("leave")) {
                Room.leaveRoom(out, roomName, userName);
            } else if(receivedMessages[0].equals("rooms")){
                Room.getRooms(out);
            }

        }
    }


}

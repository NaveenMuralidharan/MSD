import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class HttpRunnable implements Runnable{

    String fileName;
    String fileType;
    Socket socket;
    boolean isWebSocketRequest = false;
    String webSocketKey;

    public HttpRunnable(Socket socket){

        this.socket = socket;

    }

    @Override
    public void run() {

        try (InputStream input = socket.getInputStream();
                Scanner scanner = new Scanner(input)
        )

        {
            OutputStream output = socket.getOutputStream();


            Map<String, String> headers= new HashMap<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String firstLine = reader.readLine();
            String[] requestArr = firstLine.split("[/]");
            this.fileName = requestArr[1].substring(0, requestArr[1].length() - 5);
            String line;
            while((line = reader.readLine()) != null && !line.isEmpty()){
                String[] parts = line.split(": ", 2);
                if(parts.length == 2){
                    headers.put(parts[0], parts[1]);
                }
            }
            if(headers.containsKey("Sec-WebSocket-Key")){
                System.out.println("web socket request");
                System.out.println(headers.get("Sec-WebSocket-Key"));
                String webSocketKey = headers.get("Sec-WebSocket-Key");
                WebSocketHandler wsh = new WebSocketHandler(input, output, webSocketKey);
                wsh.startConnection();



            } else {
                String path = "./src/resources/" + this.fileName;
                if (fileName.isEmpty()) {
                    path = "./src/resources/index.html";
                    fileType = "html";
                }
                else {
                    this.fileType = this.fileName.split("[.]")[1];
                }

                HttpResponse response = new HttpResponse(path, fileType);
                byte[] responseBytes = response.getResponse();
                output.write(responseBytes);
                output.flush();

            }


        } catch (IOException e) {
            System.err.println("Error with input stream from client socket: "+e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}

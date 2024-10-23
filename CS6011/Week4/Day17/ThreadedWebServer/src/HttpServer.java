import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HttpServer {
    private final int port;

    public HttpServer(int port){
        this.port = port;
    }

//    public void start(){
//        try(ServerSocket serverSocket = new ServerSocket(port)) {
//
//            while (true) {
//                try(Socket socket = serverSocket.accept();
//                    InputStream input = socket.getInputStream();
//                    Scanner scanner = new Scanner(input)) {
//
//                    String[] requestArr = scanner.nextLine().split("[/]");
//                    String fileName = requestArr[1].substring(0, requestArr[1].length() - 5);
//
//                    String fileType;
//                    String path = "./src/resources/" + fileName;
//                    if (fileName.isEmpty()) {
//                        path = "./src/resources/index.html";
//                        fileType = "html";
//                    } else {
//                        String[] cssArr = fileName.split("[.]");
//                        fileType = cssArr[1];
//                    }
//                    System.out.println("path is "+ path);
//                    System.out.println("fileType is "+ fileType);
//                    HttpResponse response = new HttpResponse(path, fileType);
//                    byte[] responseBytes = response.getResponse();
//                    socket.getOutputStream().write(responseBytes);
//                    socket.getOutputStream().flush();
//
//                } catch(IOException e){
//                    System.err.println("Error handling request: "+e.getMessage());
//
//                }
//            }
//        } catch(IOException e){
//            System.err.println("Error starting server: "+e.getMessage());
//        }
//    }

    public void start(){
        try(ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    HttpRunnable httpRun = new HttpRunnable(socket);
                    Thread t = new Thread(httpRun);
                    t.start();

                } catch(IOException e){
                    System.err.println("Error handling request: "+e.getMessage());

                }
            }
        } catch(IOException e){
            System.err.println("Error starting server: "+e.getMessage());
        }
    }

}

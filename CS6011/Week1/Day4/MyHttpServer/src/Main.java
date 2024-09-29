import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8081);

        while(true){

            //setup socket to accept requests
            Socket socket = serverSocket.accept();
            //setup Inputstream to capture socket's input stream
            InputStream input = socket.getInputStream();
            //setup a scanner for inputstream
            Scanner scanner = new Scanner(input);
            String[] requestArr = scanner.nextLine().split("[/]");
            String fileName = requestArr[1].substring(0,requestArr[1].length()-5);

            String[] cssArr = fileName.split("[.]");
            String fileType = cssArr[1];

            //check if file name is in resources, if not and fileName is not empty, send 404 err
            String path = "./src/resources/"+fileName;
            if(fileName.isEmpty()){
                path = "./src/resources/index.html";
            }

            File file = new File(path);

            if(!(file.exists())){
                socket.getOutputStream().write(("HTTP/1.1 404 Not Found\r\nContent-Type: text/html\r\n\r\n<!DOCTYPE html><HTML><BODY><h1>Error 404: File Not found!</h1></BODY></HTML>").getBytes());
            }
            //else if fileName exists parse contents of file and display as html
            else {
                FileInputStream fileInput = new FileInputStream(file);
                //create a byte array of right size to hold bytes read from fileInput
                long fileLength = fileInput.getChannel().size();
                byte[] data = new byte[(int) fileLength];
                fileInput.read(data);

                byte[] headerBytes = ("HTTP/1.1 200 OK\r\nContent-Type: text/" + fileType + "\r\n\r\n").getBytes();

                int resArrLength = headerBytes.length + data.length;
                byte[] resBytes = new byte[resArrLength];
                System.arraycopy(headerBytes, 0, resBytes, 0, headerBytes.length);
                System.arraycopy(data, 0, resBytes, headerBytes.length, data.length);

                socket.getOutputStream().write(resBytes);

            }
            socket.getOutputStream().flush();

            socket.close();
        }

    }
}
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class HttpRunnable implements Runnable{

    Socket socket;

    public HttpRunnable(Socket socket){

        this.socket = socket;
    }

    @Override
    public void run() {
//        InputStream input = null;
        try (InputStream input = socket.getInputStream();
                Scanner scanner = new Scanner(input))
        {
            String[] requestArr = scanner.nextLine().split("[/]");
            String fileName = requestArr[1].substring(0, requestArr[1].length() - 5);

            String fileType;
            String path = "./src/resources/" + fileName;
            if (fileName.isEmpty()) {
                path = "./src/resources/index.html";
                fileType = "html";
            } else {
                String[] cssArr = fileName.split("[.]");
                fileType = cssArr[1];
            }
            System.out.println("path is "+ path);
            System.out.println("fileType is "+ fileType);
            HttpResponse response = new HttpResponse(path, fileType);
            byte[] responseBytes = response.getResponse();
            socket.getOutputStream().write(responseBytes);
            socket.getOutputStream().flush();
//            try{Thread.sleep(10000);} catch (InterruptedException e) {
//                System.out.println("Thread error: "+e.getMessage());
//            }

        } catch (IOException e) {
            System.err.println("Error with input stream from client socket: "+e.getMessage());
        }

    }
}

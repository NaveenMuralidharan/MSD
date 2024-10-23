import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HttpResponse {
    private final String filePath;
    private final String fileType;

    public HttpResponse(String filePath, String fileType){
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public byte[] getResponse() throws IOException {
        File file = new File(filePath);

        if (!(file.exists())) {
            return generate404Response();
        }
        else {
            return generate200Response(file);
        }
    }

    private byte[] generate404Response(){

        String errResponse = "HTTP/1.1 404 Not Found\r\nContent-Type: text/html\r\n\r\n<!DOCTYPE html><HTML><BODY><h1>Error 404: File Not found!</h1></BODY></HTML>";
        return errResponse.getBytes();

    }

    private byte[] generate200Response(File file) throws IOException{
        try(FileInputStream fileInput = new FileInputStream(file)) {
            long fileLength = fileInput.getChannel().size();
            byte[] data = new byte[(int) fileLength];
            fileInput.read(data);

            byte[] headerBytes = ("HTTP/1.1 200 OK\r\nContent-Type: text/" + fileType + "\r\n\r\n").getBytes();
            int resArrLength = headerBytes.length + data.length;
            byte[] resBytes = new byte[resArrLength];
            System.arraycopy(headerBytes, 0, resBytes, 0, headerBytes.length);
            System.arraycopy(data, 0, resBytes, headerBytes.length, data.length);
            return resBytes;
        }
    }

}

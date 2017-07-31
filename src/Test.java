import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/7/31 0031.
 */
public class Test {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(8080);
            while (true){
            Socket socket=serverSocket.accept();
            OutputStream outputStream=socket.getOutputStream();
            outputStream.write("asdsa".getBytes());
            outputStream.flush();
            outputStream.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

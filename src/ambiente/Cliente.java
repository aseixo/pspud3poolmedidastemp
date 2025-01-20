package ambiente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Cliente {

    private static final int PORT = 9000;
    private static final String ADDRESS = "127.0.0.1";
    private static final String DATA = "data";

    public static void main(String[] args) {

        try (Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT)){
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            while (true){
                outputStream.write(DATA.getBytes());
                outputStream.write(new Random().nextInt(40));
                outputStream.flush();
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

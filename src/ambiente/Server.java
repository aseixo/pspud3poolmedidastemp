package ambiente;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 9000;
    private static List<Medida> medidas = Collections.synchronizedList(new ArrayList<>());

    private static Socket clientSocket;
    public static void main(String[] args) {

        //Opción B
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (serverSocket != null) {
                clientSocket = serverSocket.accept();
                //Opción A
                // new ServerThread(clientSocket, medidas).start();
                //en caso de empregarmos un thread pool, B
                threadPool.submit(new ServerThread(clientSocket, medidas));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

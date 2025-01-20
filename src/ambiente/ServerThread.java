package ambiente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ServerThread extends  Thread{

    private Socket socket;
    private static List<Medida> medidas;
    private AtomicLong promedio;

    public ServerThread(Socket socket, List<Medida> medidas) {
        this.socket = socket;
        this.medidas = medidas;
    }

    public void run (){
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            while (true){

                byte[] bufferData = new byte[4];
                int dataBytes = inputStream.read(bufferData);
                String sensorAddress = socket.getInetAddress().toString();
                int temp = inputStream.read();

                Medida medida = new Medida(socket.getInetAddress().toString(), new Date(), temp);
                System.out.println(" >> data ".concat(medida.toString()));

                synchronized (this.medidas) {
                    medidas.add(medida);
                    promedio = new AtomicLong(estatisticas());
                    System.out.println("Promedio ".concat(String.valueOf(promedio)));

                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long estatisticas() {
        int promedio = 0;
        for (Medida m: medidas){
            promedio += m.getMedida();
        }
        promedio /= (long) medidas.size();
        return  promedio;
    }
}

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {

        //Host del servidor
        final String HOST = "127.0.0.1";
        //Puerto del servidor
        final int PUERTO = 5000;
        BufferedInputStream in;
        BufferedOutputStream out;
        byte[] byteArray;

        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTO);

            in = new BufferedInputStream(sc.getInputStream());
            out = new BufferedOutputStream(sc.getOutputStream());
            byteArray = new byte[1024];

            //Envio un mensaje al cliente
            String mensaje = "Hola!";
            out.write(mensaje.getBytes());
            out.flush();

            int n = in.read(byteArray);
            System.out.println(n);
            String mensaje1 = new String(byteArray);
            System.out.println(mensaje1);
          

            sc.close();

        } catch (IOException ex) {
            System.out.println("Error xd");
        }

    }

}
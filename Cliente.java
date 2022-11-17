import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    final String HOST = "127.0.0.1";
    //Puerto del servidor
    final int PUERTO = 5000;
    BufferedInputStream in;
    BufferedOutputStream out;
    byte[] byteArray;
    Socket sc;
    boolean jugando = true;
//GamePanel UI;

    void iniciaSocket() throws IOException{
        sc = new Socket(HOST, PUERTO);
        in = new BufferedInputStream(sc.getInputStream());
        out = new BufferedOutputStream(sc.getOutputStream());
        byteArray = new byte[1024];
        System.out.println("Se crearon los puertos");
    }
    void recibir() throws IOException{
        int n = in.read(byteArray);
        System.out.println(n);
        String mensaje = new String(byteArray);
        System.out.println(mensaje);
    }

    void enviar() throws IOException{
        out.write("Hola".getBytes());
        out.flush();
    }

    void cerrarSocket() throws IOException {
        sc.close();
        System.out.println("Cliente desconectado");
    }

    void readEnemigos(String mensaje){
        if (mensaje.substring(0, 0) == "Y"){
           // crearYeti(String mensaje.substring(1, 1), String mensaje.substring(2, 2))
            System.out.println("Se crea un yeti");
        }
        else if (mensaje.substring(0, 0) == "A"){
            //crearAve(String mensaje.substring(1, 1), String mensaje.substring(2, 2))
            System.out.println("Se crea un Ave");
        }
        else if(mensaje.substring(0, 0) == "H"){
            //crearHielo(String mensaje.substring(1, 1), String mensaje.substring(2, 2))
            System.out.println("Se crea un Hielo");
        }
    }

    void readPtos(String mensaje){
        //UI.setPuntos(mensaje)
        System.out.println("Se establecieron los puntos");
    }

    void readVidas(String mensaje){
        //UI.setVidas(mensaje)
        System.out.println("Se establecieron las vidas");
    }

}
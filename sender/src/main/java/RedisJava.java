import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.util.Scanner;


/**
 * Created by Judit Jiménez on 26/04/2017.
 */
public class RedisJava {
    static String  Host = "104.155.101.167";

    public static void main(String[] args) {
        LectorTXT L = new LectorTXT();

        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis(Host);
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        System.out.println("Server is running: " + jedis.ping());

        BufferedReader buffer = L.crearFlujoArchivo();

        System.out.println("------------------------------------------------");
        System.out.println("- Opciones:                                    -");
        System.out.println("- Pulsa 1 para actualizar las coordenadas.     -");
        System.out.println("- Pon Salir para salir de la aplicación.       -");
        System.out.println("------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        String teclaPulsada = sc.nextLine();

        while(!teclaPulsada.equalsIgnoreCase("Salir")) {
            if (teclaPulsada.equals("1")) {
                String coordenada = L.leerTXT(buffer);
                if(coordenada!=null)
                    jedis.publish("locations",coordenada);
            }
            teclaPulsada = sc.nextLine();
        }

        System.out.println("Saliendo de la aplicación. ");

    }
}
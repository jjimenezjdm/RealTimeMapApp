import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Judit Jiménez on 26/04/2017.
 */
public class LectorTXT {
    String FileName = "Coordenadas.txt";

    public BufferedReader crearFlujoArchivo(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        // Apertura del fichero y creacion de BufferedReader para poder
        // hacer una lectura comoda (disponer del metodo readLine()).
        archivo = new File(FileName);
        try {
            fr = new FileReader(archivo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        br = new BufferedReader(fr);
        return br;
    }


    public String leerTXT(BufferedReader br) {
        String linea="";
        try {
            // Lectura del fichero
            if((linea=br.readLine())!=null) {
                linea = "{\"id\":\"1\",\"position\":[" + linea + "]}";
                System.out.println();
                System.out.println("Leyendo" + linea);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return linea;
    }
}

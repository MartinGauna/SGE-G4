package martes.noche;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );

        //Loading Dispositivo.
        Dispositivo dispositivo = new Dispositivo();
        try {
            dispositivo.loadJSON("./src/main/resources/dispositivo.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "Dispositivo:" );
        System.out.print( " Nombre: " );
        System.out.println( dispositivo.getNombre());
        System.out.print( " ConsumoHora: " );
        System.out.println( dispositivo.getConsumoHora());
        System.out.print( " Estado: " );
        System.out.println( dispositivo.getEstado());
    }
}

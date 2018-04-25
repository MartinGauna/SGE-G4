package martes.noche;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import martes.noche.categoria.Categoria;
import martes.noche.json.Json;

/**
 * Hello world!
 *
 */
public class App 
{
	private static App instance = null;
	List<Categoria> categorias = new ArrayList<Categoria>();
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );

        //Loading Dispositivo.
    	Json jsonReader = new Json();
        Dispositivo dispositivo = new Dispositivo();
        try {
            dispositivo = jsonReader.loadDispositivoJSON("./src/main/resources/dispositivo.json");
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
    
    public static App getInstance() {
    	if(instance == null) { 
    		instance = new App();
    	}
    	return instance;
    }
    
    public Categoria getCategoriaByName(String name) {
    	int i;
    	for(i = 0; i < categorias.size(); i++) {
    		Categoria cat = categorias.get(i);
    		if(cat.getName() == name) {
    			return cat;
    		}
    	}
    	return null;
    }
    
    public Categoria getCategoriaByConsume(int consume) {
    	int i;
    	for(i = 0; i < categorias.size(); i++) {
    		Categoria cat = categorias.get(i);
    		if(consume > cat.getConsumoMin() && consume < cat.getConsumoMax()) {
    			return cat;
    		}
    	}
    	return null;
    }
}

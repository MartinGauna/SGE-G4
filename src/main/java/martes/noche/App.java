package martes.noche;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import martes.noche.categoria.Categoria;
import martes.noche.jsonParser.JsonParser;

public class App 
{
	private static App instance = null;
	List<Categoria> categorias = new ArrayList<Categoria>();
    public static void main( String[] args )
    {

        //Loading Dispositivo.
    	JsonParser jsonParser = new JsonParser();
        Dispositivo dispositivo = new Dispositivo();

        System.out.println( "============ Clientes" );
        try{
			Cliente cliente = jsonParser.loadClientJSON("/cliente.json");
			System.out.println(cliente.toString());
		} catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "============ Lista de Clientes" );
		try{
			List<Cliente> clientes = jsonParser.loadClientesJSON("/listaClientes.json");
            clientes.forEach(cli-> System.out.println(cli.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}

        System.out.println( "============ Dispositivo" );
        try {
            dispositivo = jsonParser.loadDispositivoJSON("/dispositivo.json");
			System.out.println(dispositivo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "============ Lista de Dispositivos" );
        try {
            List<Dispositivo> dispositivos = jsonParser.loadDispositivosJSON("/listaDispositivos.json");
            dispositivos.forEach(disp -> System.out.println(disp.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }


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

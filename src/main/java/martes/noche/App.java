package martes.noche;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        System.out.println( "============ Administrador" );
        try{
            Administrador admin = jsonParser.loadAdministradorJSON("/administrador.json");
            System.out.println(admin.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "============ Lista de Administradores" );
        try{
            List<Administrador> admins = jsonParser.loadAdministradoresJSON("/listaAdministradores.json");
            admins.forEach(a-> System.out.println(a.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "============ Lista de Dispositivos Inteligentes" );
        try {
            List<DispositivoInteligente> dispositivos = jsonParser.loadDispositivosInteligentesJSON("/listaDispositivosInteligentes.json");
            dispositivos.forEach(disp -> System.out.println(disp.getClass().toString()));


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "============ Lista de Dispositivos Estandard" );
        try {
            List<Estandard> dispositivos = jsonParser.loadDispositivosEstandardJSON("/listaDispositivosEstandard.json");
            dispositivos.forEach(disp -> System.out.println(disp.getClass().toString()));


        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println( "============ Categoria" );
        try {
            Categoria cat = new Categoria("R1");
            System.out.println(cat.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Categoria cat = new Categoria("R7");
            System.out.println(cat.toString());
        } catch (Exception e) {
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
    		if(cat.getNombre() == name) {
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

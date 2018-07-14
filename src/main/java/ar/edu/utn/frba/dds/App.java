package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import ar.edu.utn.frba.dds.helpers.BackgroundProcesses;
import ar.edu.utn.frba.dds.jsonParser.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App 
{
	private static App instance = null;
	List<Categoria> categorias = new ArrayList<Categoria>();
	Cliente loggedClient;
    public static void main( String[] args )
    {

        //Loading Dispositivo.
    	JsonParser jsonParser = new JsonParser();
        Dispositivo dispositivo = new Dispositivo();
        BackgroundProcesses bkgP = new BackgroundProcesses();


        System.out.println( "============ Lista de Clientes" );
		try{
			List<Cliente> clientes = jsonParser.loadClientesJSON();
            clientes.forEach(cli-> System.out.println(cli.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}

        System.out.println( "============ Lista de Administradores" );
        try{
            List<Administrador> admins = jsonParser.loadAdministradoresJSON();
            admins.forEach(a-> System.out.println(a.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "============ Lista de Dispositivos Inteligentes" );
        try {
            List<DispositivoInteligente> dispositivos = jsonParser.loadDispositivosInteligentesJSON();
            dispositivos.forEach(disp -> System.out.println(disp.getClass().toString()));


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "============ Lista de Dispositivos Estandard" );
        try {
            List<Estandard> dispositivos = jsonParser.loadDispositivosEstandardJSON();
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
    public Cliente getLoggedClient() {

        return loggedClient;
    }
}
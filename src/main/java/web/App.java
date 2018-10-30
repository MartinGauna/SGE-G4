package web;

import ar.edu.utn.frba.dds.Administrador;
import ar.edu.utn.frba.dds.Categoria;
import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.AdminDao;
import ar.edu.utn.frba.dds.dao.CategoriaDao;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import ar.edu.utn.frba.dds.helpers.BackgroundProcesses;
import ar.edu.utn.frba.dds.jsonParser.JsonParser;
import spark.Spark;
import spark.debug.DebugScreen;
import web.controllers.Admin.AltaDispositivoController;
import web.controllers.Admin.HogarController;
import web.controllers.LogoutController;
import web.controllers.login.LoginController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.staticFileLocation;

public class App 
{
	private static App instance = null;
	List<Categoria> categorias = new ArrayList<Categoria>();
	Cliente loggedClient;
    private static AdminDao adao = new AdminDao();
    private static ClientDao cldao = new ClientDao();
    private static CategoriaDao cadao = new CategoriaDao();

    public static void main( String[] args )
    {

        JsonParser jsonParser = new JsonParser();
        BackgroundProcesses bkgP = new BackgroundProcesses();

        //Lista de Categorias

        try{
            List<Categoria> categorias = jsonParser.loadCategoriasJSON();
            for (Categoria c : categorias) {
                //cadao.addCategoriaIfNotExists(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Lista de Clientes
        try{
            List<Cliente> clientes = jsonParser.loadClientesJSON();
            for (Cliente c : clientes) {
                c.setCategoria(null);
                cldao.addClientIfNotExists(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "============ Lista de Administradores" );
        try{
            List<Administrador> admins = jsonParser.loadAdministradoresJSON();
            for (Administrador c : admins) {
                adao.addAdminIfNotExists(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println( "============ Lista de Dispositivos Inteligentes" );
//        try {
//            List<DispositivoInteligente> dispositivos = jsonParser.loadDispositivosInteligentesJSON();
//            dispositivos.forEach(disp -> System.out.println(disp.getClass().toString()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println( "============ Lista de Dispositivos Estandard" );
        try {
            List<Estandard> dispositivos = jsonParser.loadDispositivosEstandardJSON();
            dispositivos.forEach(disp -> System.out.println(disp.getClass().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Spark.port(9000);
        staticFileLocation("/webResources");
        DebugScreen.enableDebugScreen();

        startControllers();
    }

    public static void startControllers(){
        HogarController.init();
        LoginController.init();
        LogoutController.init();
        AltaDispositivoController.init();
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
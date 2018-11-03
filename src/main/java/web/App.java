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
import web.controllers.Admin.ReportesController;
import web.controllers.LogoutController;
import web.controllers.login.LoginController;
import web.LoadData;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.staticFileLocation;

public class App 
{
	private static App instance = null;
	List<Categoria> categorias = new ArrayList<Categoria>();
	Cliente loggedClient;
	LoadData l = null;


    public static void main( String[] args ) throws ParseException {

        BackgroundProcesses bkgP = new BackgroundProcesses();

        LoadData.Load();

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
        ReportesController.init();
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
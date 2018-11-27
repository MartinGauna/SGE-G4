package web;

import ar.edu.utn.frba.dds.Categoria;
import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.GeneradorReportes;
import ar.edu.utn.frba.dds.helpers.BackgroundProcesses;
import spark.Spark;
import spark.debug.DebugScreen;
//import spark.template.handlebars.HandlebarsTemplateEngine;
import web.controllers.Admin.AltaDispositivoController;
import web.controllers.Admin.HogarController;
import web.controllers.Admin.ReportesController;
import web.controllers.LogoutController;
import web.controllers.client.EstadoDispositivoController;
import web.controllers.client.EstadoHogarController;
import web.controllers.client.UploadController;
import web.controllers.login.LoginController;

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
//    private static HandlebarsTemplateEngine engine;


    public static void main( String[] args ) throws ParseException {
//        engine = new HandlebarsTemplateEngine ();

        BackgroundProcesses bkgP = new BackgroundProcesses();

//        LoadData.Load();

        Spark.port(9000);
        staticFileLocation("/webResources");
        DebugScreen.enableDebugScreen();

        startControllers();
        handle404();
      //  while(true) {
      //      GeneradorReportes.getInstance().generarReportes();
     //   }

    }

    private static void startControllers(){
        HogarController.init();
        LoginController.init();
        LogoutController.init();
        AltaDispositivoController.init();
        ReportesController.init();
        EstadoHogarController.init();
        TransformadoresController.init();
        UploadController.init();
        EstadoDispositivoController.init();
    }

    private static void handle404() {
        Spark.get("/", (request, response) -> {
            response.redirect("/estadoHogar");
            return "";
        });
        Spark.notFound("<html><body><h1>Custom 404 handling</h1></body></html>");
//        Spark.get("*", (req, res) -> {
//            if(!req.pathInfo().startsWith("/")){
//                res.status(404);
//                return HandlebarsTemplateEngine.render ;
//            }
//            return null;
//        });
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
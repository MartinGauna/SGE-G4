package web;

import ar.edu.utn.frba.dds.Categoria;
import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.helpers.BackgroundProcesses;
import spark.Spark;
import spark.debug.DebugScreen;
import web.controllers.Admin.AltaDispositivoController;
import web.controllers.Admin.GenerarReporteController;
import web.controllers.Admin.HogarController;
import web.controllers.Admin.ReportesController;
import web.controllers.LogoutController;
import web.controllers.client.*;
import web.controllers.login.LoginController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.staticFileLocation;

//import spark.template.handlebars.HandlebarsTemplateEngine;

public class App 
{
	private static App instance = null;
	List<Categoria> categorias = new ArrayList<Categoria>();
	Cliente loggedClient;
//    private static HandlebarsTemplateEngine engine;


    public static void main( String[] args ) throws ParseException {
//        engine = new HandlebarsTemplateEngine ();

        BackgroundProcesses bkg = new BackgroundProcesses();
        bkg.automatizacionAhorroAutomatico(60000);

        LoadData.Load();

        Spark.port(getHerokuAssignedPort());
        staticFileLocation("/webResources");
        DebugScreen.enableDebugScreen();

        startControllers();
        handle404();
      //  while(true) {
      //      GeneradorReportes.getInstance().generarReportes();
     //   }

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    private static void startControllers(){
        SimplexController.init();
        HogarController.init();
        LoginController.init();
        LogoutController.init();
        AltaDispositivoController.init();
        web.controllers.client.AltaDispositivoController.init();
        ReportesController.init();
        EstadoHogarController.init();
        TransformadoresController.init();
        UploadController.init();
        //EstadoDispositivoController.init();
        AltaReglasController.init();

       // ReglaController.init();
        GenerarReporteController.init();
        SeleccionDispositivoController.init();
        SeleccionReglaController.init();

    }

    private static void handle404() {
        Spark.get("/", (request, response) -> {
            response.redirect("/estadoHogar");
            return "";
        });
      //  Spark.notFound("<html><body><h1>Custom 404 handling</h1></body></html>");
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
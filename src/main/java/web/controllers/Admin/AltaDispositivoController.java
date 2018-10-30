package web.controllers.Admin;


import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.mainController;
import web.models.AltaDispositivoModel;

public class AltaDispositivoController extends mainController {

    private static final String ALTADISPOSITIVO = "/admin/altaDispositivo.hbs";
    private static AltaDispositivoModel model;

    private static ClientDao clientDao = new ClientDao();
    private static DispositivoDao dispositivoDao = new DispositivoDao();

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.altaDispositivoPath(), AltaDispositivoController::load,engine);
        initModel();
    }

    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
        return new ModelAndView (model, ALTADISPOSITIVO); // TODO add 'Dispositivo' model
    }

    private static void initModel() {

        model = new AltaDispositivoModel();
    }

    public static void crearDispositivo(){
        System.out.println("Llegue aca");
    }

}
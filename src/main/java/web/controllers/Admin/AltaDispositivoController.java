package web.controllers.Admin;


import ar.edu.utn.frba.dds.dao.DispositivoDao;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.models.AltaDispositivoModel;

public class AltaDispositivoController extends MainController {
    private static final String ALTADISPOSITIVO = "/admin/altaDispositivo.html";
    private static AltaDispositivoModel model;

    private static DispositivoDao ddao = new DispositivoDao();

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.adminHogaresPath(), AltaDispositivoController::altaDispositivo,engine);
        initModel();
    }

    private static ModelAndView altaDispositivo(Request request, Response response) {
        sessionExist(request, response);

        return new ModelAndView (model, ALTADISPOSITIVO); // TODO add 'hogares' model
    }

    private static void initModel() {


    }

}
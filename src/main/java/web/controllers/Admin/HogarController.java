package web.controllers.Admin;
import ar.edu.utn.frba.dds.Cliente;
import com.github.jknack.handlebars.Handlebars;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.mainController;
import web.helper.SessionHelper;
import web.models.HogaresModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import ar.edu.utn.frba.dds.dao.ClienteDao;




public class HogarController extends mainController {

    private static final String HOGARES = "/admin/hogares.html";
    private static HogaresModel hogares;
//    private static ClienteDao clienteDao = new ClienteDao();

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.adminHogaresPath(), HogarController::showHogares,engine);
        initModel();
    }

    private static ModelAndView showHogares(Request request, Response response) {
        sessionExist(request, response);

        return new ModelAndView (hogares, HOGARES); // TODO add 'hogares' model
    }

    private static void initModel() {
        hogares = new HogaresModel();

        // TEST Antes de hacer el DAO
        List<Cliente> test = new ArrayList<Cliente>();
        test.add(new Cliente( "C1", "", "Dom 1", "", "", LocalDate.now(),
                "", 1, 1, null, LocalDate.now(),false));
        test.add(new Cliente( "C2", "", "Dom 2", "", "", LocalDate.now(),
                "", 1, 1, null, LocalDate.now(),false));

//        hogares.setClientes(clienteDao.getClientes());
        hogares.setClientes(test);
    }
}

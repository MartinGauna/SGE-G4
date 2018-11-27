package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.ActuadorDao;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dao.ReglaDao;
import ar.edu.utn.frba.dds.regla.Regla;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.models.ReglasModel;
import web.models.views.ReglaTable;

import java.util.ArrayList;
import java.util.List;

public class ReglaController extends MainController {
    private static final String ESTADOSDISPOSITIVOS = "/cliente/reglas.hbs";
    private static ReglasModel model;
    private static Cliente cliente;
    private static DispositivoDao ddao = new DispositivoDao();
    private static ClientDao cdao = new ClientDao();
    private static ReglaDao reglaDao = new ReglaDao();
    private static ActuadorDao actuadorDaoDao = new ActuadorDao();
    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.reglasPath(), ReglaController::showRegla,engine);

        initModel();
    }

    private static ModelAndView showRegla(Request request, Response response) {
        sessionExist(request, response);
        getCurrentClient(request);
        fillTable();
        return new ModelAndView (model, ESTADOSDISPOSITIVOS);
    }

    private static void initModel() {

        model = new ReglasModel();

    }

    private static void getCurrentClient(Request request) {
        String userSession =  request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0,userSession.indexOf("-")));
        cliente = cdao.getCliente(userID);
    }

    private static void fillTable() {

        List<ReglaTable> table = new ArrayList<ReglaTable>();
        List<Regla> reglas =  reglaDao.getAllReglas(cliente.getId());

        for (Regla r : reglas) {
            ReglaTable row = new ReglaTable();
            row.setAccion(r.getMethodName());
            row.setActuadorID(r.getActuadorID());

            table.add(row);
        }

        model.setTable(table);
    }
}
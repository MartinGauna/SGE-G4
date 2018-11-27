package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.models.EstadoDispositivosModel;
import web.models.views.EstadoDispositivosTable;

import java.util.ArrayList;
import java.util.List;

public class EstadoDispositivoController extends MainController {
    private static final String ESTADOSDISPOSITIVOS = "/cliente/estadosDispositivos.hbs";
    private static EstadoDispositivosModel model;
    private static Cliente cliente;
    private static DispositivoDao ddao = new DispositivoDao();
    private static ClientDao cdao = new ClientDao();


    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.estadosDispositivosPath(), EstadoDispositivoController::showEstados,engine);

        initModel();
    }

    private static ModelAndView showEstados(Request request, Response response) {
        sessionExist(request, response);
        getCurrentClient(request);
        fillTable();
        return new ModelAndView (model, ESTADOSDISPOSITIVOS);
    }

    private static void initModel() {

        model = new EstadoDispositivosModel();

    }

    private static void getCurrentClient(Request request) {
        String userSession =  request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0,userSession.indexOf("-")));
        cliente = cdao.getCliente(userID);
    }

    private static void fillTable() {

        List<EstadoDispositivosTable> table = new ArrayList<EstadoDispositivosTable>();
        List<Dispositivo> dispositivos = ddao.getAllDI(cliente);

        for (Dispositivo d : dispositivos) {
            EstadoDispositivosTable row = new EstadoDispositivosTable();
            row.setDispositivo(d);
            row.setEstado(d.getEstado().name());

            table.add(row);
        }

        model.setTable(table);
    }
}
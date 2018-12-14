package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.helpers.AdapterSimplex;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.models.SimplexModel;
import web.models.views.SimplexTable;

import java.util.ArrayList;
import java.util.List;

public class SimplexController extends MainController {
    private static final String SIMPLEX = "/cliente/simplex.hbs";
    private static SimplexModel model;
    private static ClientDao cdao = new ClientDao();
    private static DispositivoDao ddao = new DispositivoDao();
    private static Cliente currentClient;

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.simplexPath(), SimplexController::showSimplex, engine);
        initModel();
    }

    private static ModelAndView showSimplex(Request request, Response response) {
        sessionExist(request, response);

        String userSession = request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0, userSession.indexOf("-")));
        currentClient = cdao.getCliente(userID);

        fillSimplexTable();

        return new ModelAndView(model, SIMPLEX);
    }

    private static void initModel() {

        model = new SimplexModel();
    }

   private static void fillSimplexTable() {
       AdapterSimplex adapterSimplex = new AdapterSimplex();
        List<SimplexTable> table = new ArrayList<SimplexTable>();
        List<Dispositivo> dispositivos = ddao.getAllDispositivos(currentClient);
        double indice = 0;

        adapterSimplex.reporteConsumoEficiente(dispositivos);
       for(int i = 0;i < adapterSimplex.solucion.getPoint().length;i++)
       {
           indice = (adapterSimplex.solucion.getPoint()[i]);

           SimplexTable row = new SimplexTable();
           row.setDispositivo(dispositivos.get(i).getNombre());
           row.setSimplexindex(indice);
           table.add(row);
       }

        model.setSimplexTable(table);
    }

}
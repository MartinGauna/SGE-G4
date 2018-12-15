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
import web.models.AlertModel;
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
    private static AlertModel alert = new AlertModel(false,"",false);

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

        try
            {
                alert.setHideAlert();
                fillSimplexTable();
                return new spark.ModelAndView(model,SIMPLEX);
            }
        catch(Exception e)
            {
                alert.setShowAlertWithMessage("No existe solución dentro de los parámetros (máximo 612kWH).");
                return new ModelAndView(alert, SIMPLEX);
            }

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

        try{
       for(int i = 0;i < adapterSimplex.solucion.getPoint().length;i++)
       {
           indice = (adapterSimplex.solucion.getPoint()[i]);

           SimplexTable row = new SimplexTable();
           row.setDispositivo(dispositivos.get(i).getNombre());
           row.setIndex(Double.toString(indice));
           table.add(row);
       }
       }
       catch(Exception e){}

       model.setSimplexTable(table);
    }

}
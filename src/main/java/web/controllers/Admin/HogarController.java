package web.controllers.Admin;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.ConsumoDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.models.HogaresModel;
import web.models.views.HogaresTable;

import java.util.ArrayList;
import java.util.List;

public class HogarController extends MainController {

    private static final String HOGARES = "/admin/hogares.hbs";
    private static HogaresModel model;

    private static ClientDao cdao = new ClientDao();
    private static DispositivoDao ddao = new DispositivoDao();
    private static ConsumoDao codao = new ConsumoDao();


    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.adminHogaresPath(), HogarController::showHogares,engine);
        initModel();
    }

    private static ModelAndView showHogares(Request request, Response response) {
        sessionExist(request, response);
        fillTable();
        return new ModelAndView (model, HOGARES); // TODO add 'hogares' model
    }

    private static void initModel() {

        model = new HogaresModel();

    }

    private static long getAllConsumosByHogar(Cliente c) {
        long consumoTotal = 0;

        List<Dispositivo> dispositivos = ddao.getAllDispositivos(c);

        for (Dispositivo d : dispositivos) {
             List<Consumo> consumos =  codao.getConsumo(d);

             if (!consumos.isEmpty()) {
                 for (Consumo co : consumos) {
                     consumoTotal += co.getWatts();
                 }
             }
        }
        return consumoTotal;
    }

    private static void fillTable() {
        List<HogaresTable> table = new ArrayList<HogaresTable>();
        List<Cliente> cls = cdao.list();

        for (Cliente c : cls) {
            HogaresTable row = new HogaresTable();
            row.setCliente(c);
            row.setConsumoTotal(getAllConsumosByHogar(c));

            table.add(row);
        }
        model.setTable(table);
    }
}

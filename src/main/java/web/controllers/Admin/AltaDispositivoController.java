package web.controllers.Admin;


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
import web.models.AlertModel;
import web.models.AltaDispositivoModel;
import web.models.views.HogaresTable;

import java.util.List;

public class AltaDispositivoController extends MainController {
    private static final String ALTADISPOSITIVO = "/admin/altaDispositivo.hbs";
    private static AlertModel alert = new AlertModel(false,"",false);

    private static AltaDispositivoModel model;

    private static ClientDao clientDao = new ClientDao();
    private static DispositivoDao dispositivoDao = new DispositivoDao();

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.altaDispositivoPath(), AltaDispositivoController::load,engine);
        Spark.post(Router.altaDispositivoPath(),AltaDispositivoController::crearDispositivo,engine);
        initModel();
    }

    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
        return new ModelAndView (model, ALTADISPOSITIVO); // TODO add 'Dispositivo' model
    }

    private static void initModel() {

        model = new AltaDispositivoModel();
        List<Cliente> cls = clientDao.list();

        for (Cliente c : cls) {
            model.getClientes().add(c);
        }
    }

    public static ModelAndView crearDispositivo(Request request, Response response){

        String tipo = request.queryParams("tipo");
        String disp = request.queryParams("dispositivo");
        String nombre = request.queryParams("nombre");
        String consumo = request.queryParams("consumoHora");
        String estado = request.queryParams("estado");
        String username = request.queryParams("cliente");

        System.out.println(tipo);
        System.out.println(disp);
        System.out.println(nombre);
        System.out.println(consumo);
        System.out.println(estado);
        System.out.println(username);

        return new ModelAndView (model, ALTADISPOSITIVO);
    }

}
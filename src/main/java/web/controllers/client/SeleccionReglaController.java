package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.dao.BaseDao;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dao.ReglaDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import ar.edu.utn.frba.dds.regla.Regla;
import ar.edu.utn.frba.dds.sensor.Sensor;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.models.seleccionDispositivoModel;
import web.models.seleccionReglaModel;
import web.models.views.ReglaPullDown;
import web.models.views.ReglaTable;

import java.util.ArrayList;
import java.util.List;

public class SeleccionReglaController extends MainController {

    private static final String SELECCION_REGLA = "/cliente/seleccionRegla.hbs";
    private static seleccionReglaModel model;
    private static Cliente cliente;
    private static ClientDao cdao = new ClientDao();
    private static DispositivoDao ddao = new DispositivoDao();
    private static ReglaDao rdao = new ReglaDao();
    private static BaseDao bdao = new BaseDao();


    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.bajaModificacionReglaPath(), SeleccionReglaController::load, engine);
        Spark.post(Router.modificarReglaPath(), SeleccionReglaController::modificar, engine);
        Spark.delete(Router.bajaReglaPath(), SeleccionReglaController::delete, engine);
        initModel();
    }

    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
        getCurrentClient(request);
        model.setShowAlert(false);
        model.getReglas().clear();
        fillReglas();

        return new ModelAndView(model, SELECCION_REGLA);
    }

    private static void initModel() {
        model = new seleccionReglaModel();
    }


    public static ModelAndView delete(Request request, Response response) {

        //datos del form:
        int reglaID = Integer.parseInt(request.params(":id"));
        Regla r = rdao.getReglaByID(reglaID);
        List<Object> toDelete = new ArrayList<>();

        toDelete.add(r);
        bdao.deleteList(toDelete);

        return new ModelAndView( updateModel(), SELECCION_REGLA);
    }

    private static void getCurrentClient(Request request) {
        String userSession = request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0, userSession.indexOf("-")));
        cliente = cdao.getCliente(userID);
    }

    public static ModelAndView modificar(Request request, Response response) {
        //todo
        return new ModelAndView(model, SELECCION_REGLA);
    }

    private static seleccionReglaModel updateModel(){
        model.getReglas().clear();
        fillReglas();
        model.success("La regla fue eliminada exitosamente");
        return model;
    }

    private static void fillReglas() {
        List<DispositivoInteligente> dispositivos = ddao.getAllDI(cliente);

        for (DispositivoInteligente d : dispositivos) {

                Regla r = rdao.getReglaByActuadorID((d.getActuador().getId()));
                if (r != null) {
                    r.ejecutar(d);
                    ReglaPullDown row = new ReglaPullDown();
                    row.setId(r.getId());
                    row.setAccion(r.getMethodName());
                    row.setDispositivo(d.getNombre());
                    model.getReglas().add(row);

            }
        }
    }
}
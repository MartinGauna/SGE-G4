package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.dao.BaseDao;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.sensor.Sensor;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.models.seleccionDispositivoModel;

import java.util.ArrayList;
import java.util.List;

public class SeleccionDispositivoController extends MainController {

    private static final String SELECCION_DISPOSITIVO = "/cliente/seleccionDispositivo.hbs";
    private static seleccionDispositivoModel model;
    private static Cliente cliente;
    private static ClientDao cdao = new ClientDao();
    private static DispositivoDao ddao = new DispositivoDao();
    private static BaseDao bdao = new BaseDao();


    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.bajaModificacionDispositivoPath(), SeleccionDispositivoController::load, engine);
//        Spark.post(Router.modificarDispositivoPath(), SeleccionDispositivoController::modificar, engine);
        Spark.delete(Router.bajaDispositivoPath(), SeleccionDispositivoController::delete, engine);
        Spark.put(Router.modificarDispositivoPath(), SeleccionDispositivoController::modificar, engine);
        initModel();
    }

    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
        getCurrentClient(request);
        model.setShowAlert(false);
        List<Dispositivo> dispositivos = ddao.getAllDispositivos(cliente);
        model.getDispositivos().clear();
        dispositivos.forEach(d -> model.getDispositivos().add(d));

        return new ModelAndView(model, SELECCION_DISPOSITIVO);
    }

    private static void initModel() {
        model = new seleccionDispositivoModel();
    }


    public static ModelAndView delete(Request request, Response response) {
        Actuador actuador;
        Sensor sensor;

        //datos del form:
        int dispID = Integer.parseInt(request.params(":id"));
        Dispositivo d = ddao.getDispositivo(dispID);
        List<Object> toDelete = new ArrayList<>();

        toDelete.add(d);
        bdao.deleteList(toDelete);

        model.success("El dispositivo fue eliminado exitosamente");
        return new ModelAndView( updateModel(), SELECCION_DISPOSITIVO);
    }

    private static void getCurrentClient(Request request) {
        String userSession = request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0, userSession.indexOf("-")));
        cliente = cdao.getCliente(userID);
    }

    public static ModelAndView modificar(Request request, Response response) {

        Actuador actuador;
        Sensor sensor;

        System.out.println(request.body());
        String[] parameters = request.body().split("&");

        int dispId = Integer.parseInt((parameters[0].split("="))[1]);
        String nombre = (parameters[1].split("="))[1];
        String estado = (parameters[2].split("="))[1];
        double consumoHora = Double.parseDouble((parameters[3].split("="))[1]);

        Dispositivo d = ddao.getDispositivo(dispId);
        d.setConsumoHora(consumoHora);
        d.setNombre(nombre);
        d.setEstado(estado);

        BaseDao bdao = new BaseDao();
        bdao.update(d);

        model.success("El dispositivo fue modificado exitosamente");
        return new ModelAndView( updateModel(), SELECCION_DISPOSITIVO);
    }

    private static seleccionDispositivoModel updateModel(){
        model.getDispositivos().clear();
        List<Dispositivo> dispositivos = ddao.getAllDispositivos(cliente);
        model.getDispositivos().addAll(dispositivos);
        model.success("El dispositivo fue eliminado exitosamente");

        return model;
    }
}

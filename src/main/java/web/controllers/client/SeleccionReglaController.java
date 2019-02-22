package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Magnitud;
import ar.edu.utn.frba.dds.dao.*;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.regla.Condicion;
import ar.edu.utn.frba.dds.regla.Regla;
import ar.edu.utn.frba.dds.sensor.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.models.seleccionReglaModel;
import web.models.views.ReglaPullDown;

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
    private static CondicionDao condicionDao = new CondicionDao();


    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.bajaModificacionReglaPath(), SeleccionReglaController::load, engine);
        Spark.put(Router.modificarReglaPath(), SeleccionReglaController::modificar, engine);
        Spark.delete(Router.bajaReglaPath(), SeleccionReglaController::delete, engine);
    }

    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
        getCurrentClient(request);
        initModel();
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

        model.success("La regla fue eliminada exitosamente");
        return new ModelAndView( updateModel(), SELECCION_REGLA);
    }

    private static void getCurrentClient(Request request) {
        String userSession = request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0, userSession.indexOf("-")));
        cliente = cdao.getCliente(userID);
    }

    public static ModelAndView modificar(Request request, Response response) {
        int reglaID = Integer.parseInt(request.queryParams("id"));
        Regla r = rdao.getReglaByID(reglaID);
        int cantidad = Integer.parseInt(request.queryParams("cantidad"));
        String method = request.queryParams("accion");
        String magnitudC = request.queryParams("condicionCMagnitud");
        String condicionV = request.queryParams("condicionCriterio");
        char condicion = condicionV.charAt(0);
        int condicionValor = Integer.parseInt(request.queryParams("condicionValor"));
        DispositivoInteligente d = r.getActuador().getDispositivo();
        Sensor  sensor = getSensores(d, magnitudC);
        Magnitud magnitud = sensor.getMagnitud();
        Long magnituddelsensor = magnitud.getValor();
        List<Condicion> condicions = r.getCondiciones();
        Condicion c = condicions.get(0);
        c.setCriterio(condicion);
        c.setMagnitudDelSensor(magnituddelsensor);
        c.setValor_condicion(condicionValor);
        r.setCantidad(cantidad);
        r.setMethodname(method);
        BaseDao bdao = new BaseDao();
        bdao.update(c);
        bdao.update(r);
        model.success("La regla fue modificado exitosamente");
        return new ModelAndView( updateModel(), SELECCION_REGLA);
    }

    private static seleccionReglaModel updateModel(){
        model.getReglas().clear();
        fillReglas();
        model.success("La regla fue eliminada exitosamente");
        return model;
    }

    private static void fillReglas() {

        List<Regla> r = rdao.list();
        for(int i = 0; i < r.size(); i++){
            Regla regla = r.get(i);
            regla.ejecutar();
            ReglaPullDown row = new ReglaPullDown();
            row.setId(regla.getId());
            row.setAccion(regla.getMethodName());
            row.setDispositivo(regla.getActuador().getDispositivo().getNombre());
            List<Condicion> condList = condicionDao.getCondiciones(regla);
            Condicion cond = condList.get(0);
            row.setValorCondicion(cond.getValor_condicion());
            row.setCantidad(regla.getCantidad());
            model.getReglas().add(row);
        }
    }

    private static Sensor getSensores(DispositivoInteligente d, String sensorTitulo) {

        Sensor sensor = null;

        // chequeo si el sensor ya existe.
        for (Sensor s : d.getSensores()) {
            if (s.getClass().getSimpleName().equals(sensorTitulo)) {
                sensor = s;
                break;
            }
        }

        if (sensor == null) {
            if (sensorTitulo == "Humedad") {
                sensor = new SensorHumedad();
            } else if (sensorTitulo == "Luz") {
                sensor = new SensorLuz();
            } else if (sensorTitulo == "Movimiento") {
                sensor = new SensorMovimiento();
            } else if (sensorTitulo == "Temperatura") {
                sensor = new SensorTemperatura();
            } else {
                sensor = new SensorLuz();
            }
        }
        return sensor;
    }
}
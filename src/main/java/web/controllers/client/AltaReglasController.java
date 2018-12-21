package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Magnitud;
import ar.edu.utn.frba.dds.actuador.*;
import ar.edu.utn.frba.dds.dao.*;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import ar.edu.utn.frba.dds.exception.IncompleteFormException;
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
import web.models.AlertModel;
import web.models.AltaReglasModel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class AltaReglasController extends MainController {

    private static final String ALTA_REGLAS = "/cliente/altaReglas.hbs";
    private static AltaReglasModel model;
    private static ClientDao clientDao = new ClientDao();
    private static DispositivoDao dispositivoDao = new DispositivoDao();
    private static AlertModel alert = new AlertModel(false,"",false);
    private static ReglaDao reglaDao = new ReglaDao();
    private static ActuadorDao actuadorDao = new ActuadorDao();
    private static CondicionDao condicionDao = new CondicionDao();

    private static Cliente cliente;
    private static ClientDao cdao = new ClientDao();
    private static DispositivoDao ddao = new DispositivoDao();
    private static BaseDao bdao = new BaseDao();

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.altaReglasPath(), AltaReglasController::load, engine);
        Spark.post(Router.altaReglasPath(), AltaReglasController::crearRegla, engine);
        initModel();
    }

    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
       //MATIAS OJO
        getCurrentClient(request);
        model.getDispositivos().clear();
        model.setShowAlert(false);
        List<Dispositivo> dispositivos = ddao.getAllDispositivos(cliente);

        for (Dispositivo d : dispositivos) {
            if (!(d instanceof Estandard)) {
                model.getDispositivos().add(d);
            }
        }

        return new ModelAndView(model, ALTA_REGLAS);
    }

    private static void initModel() {
        model = new AltaReglasModel();
    }


    public static ModelAndView crearRegla(Request request, Response response) {
        Actuador actuador;
        Sensor sensor;

        try {
            //datos del form:

            int dispID = Integer.parseInt(request.queryParams("dispositivo"));
            String methodName = request.queryParams("accion");
            char criterio = request.queryParams("criterio").charAt(0);
            String sensorTitulo = request.queryParams("sensor");
            Long valorCondicion = parseLong(request.queryParams("valorCondicion"));
            DispositivoInteligente d = ddao.getDI(dispID);

            sensor = getSensores(d, sensorTitulo);
            actuador = d.getActuador();

            Magnitud magnitud = sensor.getMagnitud();

            Long magnituddelsensor = magnitud.getValor();

            Condicion condicion = new Condicion(criterio, magnituddelsensor, valorCondicion);
            Regla regla = new Regla(actuador, methodName, null);
            regla.addCondicion(condicion);
            condicion.setRegla(regla);

            List<Object> persist = new ArrayList<>();
            persist.add(regla);
            persist.add(condicion);
            //persist.add(magnitud);
            //persist.add(sensor);
            //persist.add(actuador);
            //persist.add(d);
            bdao.persistList(persist);

            model.success("La regla fue creada con exito");

        } catch (IncompleteFormException ex) {
            response.status(410);
            response.body(ex.getMessage());
            model.failed(ex.getMessage());
        } catch (Exception ex) {
            response.status(400);
            response.body("Ocurrio un error. Intenta nuevamente");
            model.failed(ex.getMessage());
        }

        return new ModelAndView(model, ALTA_REGLAS);
    }

    private static void getCurrentClient(Request request) {
        String userSession = request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0, userSession.indexOf("-")));
        cliente = cdao.getCliente(userID);
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

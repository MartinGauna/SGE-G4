package web.controllers.client;


import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Magnitud;
import ar.edu.utn.frba.dds.actuador.*;
import ar.edu.utn.frba.dds.dao.*;
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

    private static Request r;
    private static final String ALTA_REGLAS = "/cliente/altaReglas.hbs";
    private static AltaReglasModel model;
    private static ClientDao clientDao = new ClientDao();
    private static DispositivoDao dispositivoDao = new DispositivoDao();
    private static AlertModel alert = new AlertModel(false,"",false);
    private static ReglaDao reglaDao = new ReglaDao();
    private static ActuadorDao actuadorDao = new ActuadorDao();
    private static CondicionDao condicionDao = new CondicionDao();
    private static BaseDao bdao = new BaseDao();

    public static void init() {

        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.altaReglasPath(), AltaReglasController::load,engine);
        Spark.post(Router.altaReglasPath(), AltaReglasController::crearRegla,engine);
        initModel();
    }

    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
        r = request;
        return new ModelAndView (model, ALTA_REGLAS);
    }

    private static void initModel() {

        model = new AltaReglasModel();
        List<Cliente> cls = clientDao.list();

        for (Cliente c : cls) {
            model.getClientes().add(c);
        }
    }

    public static ModelAndView crearRegla(Request request, Response response){
        try {
            Actuador actuador;
            Sensor sensor;
            int actuadorTipo = Integer.parseInt(request.queryParams("actuador"));
            String methodName = request.queryParams("accion");
            char criterio = request.queryParams("criterio").charAt(0);
            String sensorTitulo = request.queryParams("sensor");
            Long valorCondicion = parseLong(request.queryParams("valorCondicion"));

            if(sensorTitulo == "Humedad"){sensor = new SensorHumedad();}
            else if(sensorTitulo == "Luz"){sensor = new SensorLuz();}
            else if(sensorTitulo == "Movimiento"){sensor = new SensorMovimiento();}
            else if(sensorTitulo == "Temperatura"){sensor = new SensorTemperatura();}
            else {sensor = new SensorLuz();}

            Magnitud magnitud = sensor.getMagnitud();
            Long magnituddelsensor =  magnitud.getValor();

            if(actuadorTipo == 0){ actuador = new ActuadorAAcondicionado();}
            else if(actuadorTipo == 1){ actuador = new ActuadorHeladera();}
            else if(actuadorTipo == 2){ actuador = new ActuadorLavarropas();}
            else if(actuadorTipo == 3){ actuador = new ActuadorLuz();}
            else if(actuadorTipo == 4){ actuador = new ActuadorMicro();}
            else if(actuadorTipo == 5){ actuador = new ActuadorPC();}
            else if(actuadorTipo == 6){ actuador = new ActuadorPlancha();}
            else if(actuadorTipo == 7){ actuador = new ActuadorTV();}
            else if(actuadorTipo == 8){ actuador = new ActuadorVentilador();}
            else {actuador = new ActuadorVentilador();}
            Condicion condicion = new Condicion(criterio,magnituddelsensor,valorCondicion);
            Regla regla = new Regla(actuador,methodName,null);
            regla.addCondicion(condicion);

            List<Object> persist = new ArrayList<>();
            persist.add(actuador);
            persist.add(regla);
            persist.add(condicion);
//            actuadorDao.addActuadorIfNotExists(actuador);
//            reglaDao.addReglaIfNotExists(regla);
//            condicionDao.persist(condicion);

            bdao.persistList(persist);

            model.success("La regla fue creado con exito");
        }catch (IncompleteFormException ex){
            response.status(410);
            response.body(ex.getMessage());
            model.failed(ex.getMessage());
        }catch (Exception ex){
            response.status(400);
            response.body("Ocurrio un error. Intenta nuevamente");
            model.failed(ex.getMessage());
        }

        return new ModelAndView (model, ALTA_REGLAS);
    }

}
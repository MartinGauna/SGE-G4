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
import web.models.seleccionDispositivoModel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

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
        Spark.post(Router.bajaDispositivoPath(), SeleccionDispositivoController::delete, engine);
        Spark.post(Router.modificarDispositivoPath(), SeleccionDispositivoController::modificar, engine);
        initModel();
    }

    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
        getCurrentClient(request);
        List<Dispositivo> dispositivos = ddao.getAllDispositivos(cliente);
        dispositivos.forEach(d -> model.getDispositivos().add(d));

        return new ModelAndView(model, SELECCION_DISPOSITIVO);
    }

    private static void initModel() {
        model = new seleccionDispositivoModel();
    }


    public static ModelAndView delete(Request request, Response response) {
        Actuador actuador;
        Sensor sensor;

        try {
            //datos del form:
            int dispID = Integer.parseInt(request.queryParams("dispositivo"));
            DispositivoInteligente d = ddao.getDI(dispID);
            List<Object> toDelete = new ArrayList<>();

            toDelete.add(d);

         /*   if (!(d instanceof Estandard)) {
                sensor = d.getSensores();
                actuador = d.getActuador();
            }

            Magnitud magnitud = sensor.getMagnitud();
            Long magnituddelsensor = magnitud.getValor();

            Condicion condicion = new Condicion(criterio, magnituddelsensor, valorCondicion);
            Regla regla = new Regla(actuador, methodName, null);
            regla.addCondicion(condicion);
            condicion.setRegla(regla);


            persist.add(regla);
            persist.add(condicion);
            //persist.add(magnitud);
            //persist.add(sensor);
            //persist.add(actuador);
            //persist.add(d);*/


            //bdao.deleteList(toDelete);
        } catch (IncompleteFormException ex) {
            response.status(410);
            response.body(ex.getMessage());
            model.failed(ex.getMessage());
        } catch (Exception ex) {
            response.status(400);
            response.body("Ocurrio un error. Intenta nuevamente");
            model.failed(ex.getMessage());
        }

        model.success("La regla fue creada con exito");
        return new ModelAndView(model, SELECCION_DISPOSITIVO);
    }

    private static void getCurrentClient(Request request) {
        String userSession = request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0, userSession.indexOf("-")));
        cliente = cdao.getCliente(userID);
    }


    public static ModelAndView modificar(Request request, Response response) {

        return new ModelAndView(model, SELECCION_DISPOSITIVO);
    }
}

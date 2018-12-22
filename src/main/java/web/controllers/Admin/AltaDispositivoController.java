package web.controllers.Admin;


import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.dao.BaseDao;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.ConsumoDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.DIFactory;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import ar.edu.utn.frba.dds.exception.IncompleteFormException;
import ar.edu.utn.frba.dds.sensor.Sensor;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static ar.edu.utn.frba.dds.Consumo.createConsumo;

public class AltaDispositivoController extends MainController {
    private static final String ALTADISPOSITIVO = "/admin/altaDispositivo.hbs";

    private static AltaDispositivoModel model;

    private static ClientDao clientDao = new ClientDao();
    private static DispositivoDao dispositivoDao = new DispositivoDao();
    private static BaseDao bdao = new BaseDao();


    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.altaDispositivoPath(), AltaDispositivoController::load,engine);
        Spark.post(Router.altaDispositivoPath(),AltaDispositivoController::crearDispositivo,engine);
        initModel();
    }

    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
        model.setShowAlert(false);
        updateModel();
        return new ModelAndView (model, ALTADISPOSITIVO);
    }

    private static void initModel() {
        model = new AltaDispositivoModel();
        fillModel();
    }

    public static ModelAndView crearDispositivo(Request request, Response response){
        try {
            parseRequest(request);
            model.success("El dispositivo fue creado con exito");
        }catch (IncompleteFormException ex){
            response.status(410);
            response.body(ex.getMessage());
            model.failed(ex.getMessage());
        }catch (Exception ex){
            response.status(400);
            response.body("Ocurrio un error. Intenta nuevamente");
            model.failed(ex.getMessage());
        }

        return new ModelAndView (model, ALTADISPOSITIVO);
    }

    private static Dispositivo parseRequest(Request request) {
        //model.getAlert().setHideAlert();

        String tipo = request.queryParams("tipo");
        String disp = request.queryParams("dispositivo");
        String nombre = request.queryParams("nombre");
        double consumo = Double.parseDouble(request.queryParams("consumo"));
        String estado = request.queryParams("estado");
        String username = request.queryParams("cliente");
        boolean bajoConsumo = Boolean.valueOf(request.queryParams("bajoConsumo"));

        Dispositivo dispositivo;
        Consumo cons;
        Cliente cliente = clientDao.clientExists(username);
        DIFactory fm = new DIFactory();

        try {
            dispositivo = fm.crearDispositivoFromPOST(tipo,disp,nombre,consumo, estado, cliente, bajoConsumo);
            cons = createConsumo(dispositivo);

            List<Object> topersist = new ArrayList<>();

            if(!(dispositivo instanceof Estandard)) {
                if (!((DispositivoInteligente)dispositivo).getSensores().isEmpty()) {
                    for (Sensor s : ((DispositivoInteligente)dispositivo).getSensores()) {
                        topersist.add(s.getMagnitud());
                        topersist.add(s);
                    }
                }
                Actuador a = ((DispositivoInteligente) dispositivo).getActuador();
                topersist.add(a);
            }
            topersist.add(dispositivo);
            topersist.add(cons);
            bdao.persistList(topersist);
            clientDao.addClientIfNotExists(cliente);

        }catch (NullPointerException | ParseException ex){
            throw new IncompleteFormException();
        }
        return dispositivo;

    }


    private static void fillModel() {
        List<Cliente> cls = clientDao.list();

        for (Cliente c : cls) {
            model.getClientes().add(c);
        }
    }

    private static void updateModel() {
        model.getClientes().clear();
        List<Cliente> cls = clientDao.list();

        for (Cliente c : cls) {
            if (!model.getClientes().contains(c)) {
                model.getClientes().add(c);
            }
        }
    }


}
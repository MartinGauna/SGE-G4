package web.controllers.client;


import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.DIFactory;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import ar.edu.utn.frba.dds.exception.IncompleteFormException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.helper.SessionHelper;
import web.models.AlertModel;
import web.models.AltaDispositivoClienteModel;

import java.util.List;

public class AltaDispositivoController extends MainController {

    private static final String ALTADISPOSITIVOCLIENTE = "/cliente/altaDispositivoCliente.hbs";
    private static AltaDispositivoClienteModel model;
    private static ClientDao clientDao = new ClientDao();
    private static DispositivoDao dispositivoDao = new DispositivoDao();
    private static AlertModel alert = new AlertModel(false,"",false);

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.altaDispositivoClientePath(), AltaDispositivoController::load,engine);
        Spark.post(Router.altaDispositivoClientePath(), AltaDispositivoController::crearDispositivo,engine);
        initModel();
    }

    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
        return new ModelAndView (model, ALTADISPOSITIVOCLIENTE); // TODO add 'Dispositivo' model
    }

    private static void initModel() {

        model = new AltaDispositivoClienteModel();
        List<Cliente> cls = clientDao.list();

        for (Cliente c : cls) {
            model.getClientes().add(c);
        }
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

        return new ModelAndView (model, ALTADISPOSITIVOCLIENTE);
    }

    private static Dispositivo parseRequest(Request request) {
        //model.getAlert().setHideAlert();

        String tipo = request.queryParams("tipo");
        String disp = request.queryParams("dispositivo");
        String nombre = request.queryParams("nombre");
        double consumo = Double.parseDouble(request.queryParams("consumoHora"));
        String estado = request.queryParams("estado");
        String username = SessionHelper.getUserSession(request).substring(2);
        boolean bajoConsumo = Boolean.valueOf(request.queryParams("bajoConsumo"));


        Dispositivo dispositivo;
        Cliente cliente = clientDao.clientExists(username);
        DIFactory fm = new DIFactory();
        if(tipo == "int")
        {
            try {
                dispositivo = fm.crearDispositivoFromPOST(tipo,disp,nombre,consumo, estado, cliente, bajoConsumo);
                dispositivoDao.addDispositivoIfNotExists(dispositivo);
                clientDao.addClientIfNotExists(cliente);
            }catch (NullPointerException ex){
                throw new IncompleteFormException();
            }
        }
        else
        {
            try{
            dispositivo = new Estandard();
            dispositivo.setConsumoHora(consumo);
            dispositivo.setNombre(nombre);
            dispositivo.setCliente(cliente);
            dispositivoDao.addDispositivoIfNotExists(dispositivo);
            } catch (NullPointerException ex){
            throw new IncompleteFormException();
        }
        }
        return dispositivo;

    }

}
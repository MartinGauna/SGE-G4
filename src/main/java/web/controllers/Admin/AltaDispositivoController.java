package web.controllers.Admin;


import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.DIFactory;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.exception.IncompleteFormException;
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
        updateModel();
        return new ModelAndView (model, ALTADISPOSITIVO); // TODO add 'Dispositivo' model
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
        double consumo = Double.parseDouble(request.queryParams("consumoHora"));
        String estado = request.queryParams("estado");
        String username = request.queryParams("cliente");
        boolean bajoConsumo = Boolean.valueOf(request.queryParams("bajoConsumo"));


        Dispositivo dispositivo;
        Cliente cliente = clientDao.clientExists(username);
        DIFactory fm = new DIFactory();

        try {
            dispositivo = fm.crearDispositivoFromPOST(tipo,disp,nombre,consumo, estado, cliente, bajoConsumo);

            if (tipo.equals("int")) {
                dispositivoDao.addDispositivoInteligenteIfNotExists((DispositivoInteligente) dispositivo);
            }
            else {
                dispositivoDao.addDispositivoIfNotExists(dispositivo);
            }
            clientDao.addClientIfNotExists(cliente);
        }catch (NullPointerException ex){
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
        List<Cliente> cls = clientDao.list();

        for (Cliente c : cls) {
            if (!model.getClientes().contains(c)) {
                model.getClientes().add(c);
            }
        }
    }

}
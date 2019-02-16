package web.controllers.Admin;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.GeneradorReportes;
import ar.edu.utn.frba.dds.Transformador;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.TransformadorDao;
import ar.edu.utn.frba.dds.exception.IncompleteFormException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.models.AltaReporteModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.List;

public class GenerarReporteController extends MainController {
    private static final String ALTAREPORTE = "/admin/altaReporte.hbs";
    private static AltaReporteModel model;
    private static ClientDao clientDao = new ClientDao();
    private static TransformadorDao tdao = new TransformadorDao();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.generarReportePath(), GenerarReporteController::load, engine);
        Spark.post(Router.generarReporteClientesPath(), GenerarReporteController::crearReporteCliente, engine);
        Spark.post(Router.generarReporteTransformadorPath(), GenerarReporteController::crearReporteTransformador, engine);
        initModel();
    }


    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
        model.setHideAlert1();
        return new ModelAndView(model, ALTAREPORTE);
    }

    private static void initModel() {
        model = new AltaReporteModel();
        fillModel();
    }

    public static ModelAndView crearReporteCliente(Request request, Response response) {
        try {
            parseRequestCliente(request);
            model.success1("El reporte fue creado con exito");
        } catch (IncompleteFormException ex) {
            response.status(410);
            response.body(ex.getMessage());
            model.failed(ex.getMessage());
        } catch (EmptyStackException ex) {
            model.failed("Error: Se intento crear un reporte que ya existe");
        } catch (Exception ex) {
            response.status(400);
            response.body("Ocurrio un error. Intenta nuevamente");
            model.failed(ex.getMessage());
        }

        return new ModelAndView(model, ALTAREPORTE);
    }

    private static void parseRequestCliente(Request request) throws ParseException {
        //model.getAlert().setHideAlert();

        String tipo = request.queryParams("tipoReporte");
        String inicio = request.queryParams("fechainicio1");
        String fin = request.queryParams("fechafin1");
        String username = request.queryParams("cliente");

        Cliente cliente = clientDao.clientExists(username);
        GeneradorReportes gen = GeneradorReportes.getInstance();

        Date i = sdf.parse(inicio);
        Date f = sdf.parse(fin);

        try {
            if (tipo.equals("1")) {
                gen.getRdao().addReporte(gen.generarReporteConsumoHogar(cliente, i, f));
            } else if (tipo.equals("2")) {
                gen.getRdao().addReporte(gen.generarReporteConsumoInteligente(cliente, i, f));
            } else if (tipo.equals("3")) {
                gen.getRdao().addReporte(gen.generarReporteConsumoEstandard(cliente, i, f));
            }
        } catch (NullPointerException ex) {
            throw new IncompleteFormException();
        }
    }

    private static void fillModel() {

        List<Cliente> cls = clientDao.list();
        for (Cliente c : cls) {
            model.getClientes().add(c);
        }

        List<Transformador> ts = tdao.list();
        for (Transformador t : ts) {
            model.getTransformadores().add(t);
        }
    }

    public static ModelAndView crearReporteTransformador(Request request, Response response) {
        try {
            parseRequestTransformador(request);
            model.success1("El reporte fue creado con exito");
        } catch (IncompleteFormException ex) {
            response.status(410);
            response.body(ex.getMessage());
            model.failed(ex.getMessage());
        } catch (EmptyStackException ex) {
            model.failed("Error: Se intento crear un reporte que ya existe");
        } catch (Exception ex) {
            response.status(400);
            response.body("Ocurrio un error. Intenta nuevamente");
            model.failed(ex.getMessage());
        }

        return new ModelAndView(model, ALTAREPORTE);
    }

    private static void parseRequestTransformador(Request request) throws ParseException {
        //model.getAlert().setHideAlert();


        String inicio = request.queryParams("fechainicio2");
        String fin = request.queryParams("fechafin2");
        int trafoID = Integer.parseInt(request.queryParams("transformador"));
        Transformador t = tdao.getTransformadorByID(trafoID);

        GeneradorReportes gen = GeneradorReportes.getInstance();

        Date i = sdf.parse(inicio);
        Date f = sdf.parse(fin);

        try {
            gen.getRdao().addReporteTrafo(gen.generarReporteTransformador(t, i, f));
        } catch (NullPointerException ex) {
            throw new IncompleteFormException();
        }
    }
}

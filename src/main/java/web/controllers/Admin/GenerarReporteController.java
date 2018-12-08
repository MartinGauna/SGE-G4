package web.controllers.Admin;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.GeneradorReportes;
import ar.edu.utn.frba.dds.Reporte;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.exception.IncompleteFormException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.models.AltaReporteModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GenerarReporteController extends MainController {
    private static final String ALTAREPORTE = "/admin/altaReporte.hbs";
    private static AltaReporteModel model;
    private static ClientDao clientDao = new ClientDao();

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.generarReportePath(), GenerarReporteController::load, engine);
        Spark.post(Router.generarReportePath(), GenerarReporteController::crearReporte, engine);
        initModel();
    }


    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
        return new ModelAndView(model, ALTAREPORTE);
    }

    private static void initModel() {
        model = new AltaReporteModel();
        fillModel();
    }

    public static ModelAndView crearReporte(Request request, Response response) {
        try {
            parseRequest(request);
            model.success("El reporte fue creado con exito");
        } catch (IncompleteFormException ex) {
            response.status(410);
            response.body(ex.getMessage());
            model.failed(ex.getMessage());
        } catch (Exception ex) {
            response.status(400);
            response.body("Ocurrio un error. Intenta nuevamente");
            model.failed(ex.getMessage());
        }

        return new ModelAndView(model, ALTAREPORTE);
    }

    private static void parseRequest(Request request) throws ParseException {
        //model.getAlert().setHideAlert();

        String tipo = request.queryParams("tipoReporte");
        String inicio = request.queryParams("fechainicio");
        String fin = request.queryParams("fechafin");
        String username = request.queryParams("cliente");

        Cliente cliente = clientDao.clientExists(username);
        GeneradorReportes gen = GeneradorReportes.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
    }
}

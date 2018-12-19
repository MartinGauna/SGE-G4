package web.controllers.Admin;

import ar.edu.utn.frba.dds.*;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.ReporteDao;
import ar.edu.utn.frba.dds.dao.TransformadorDao;

import mongo.MongoDb;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.models.ReportesModel;
import web.controllers.MainController;
import web.models.views.ReportesTable;
import web.models.views.ReportesTrafosTable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReportesController extends MainController {

    private static final String REPORTES = "/admin/reportes.hbs";
    private static ReportesModel model;

    private static ClientDao cdao = new ClientDao();
    private static TransformadorDao tdao = new TransformadorDao();
    private static ReporteDao rdao = new ReporteDao();
    private static MongoDb mongo = MongoDb.getInstance();


    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.reportesPath(), ReportesController::showReportes,engine);
        initModel();
    }

    private static ModelAndView showReportes(Request request, Response response) throws ParseException {
        List<Cliente> listCliente = cdao.list();
        List<Transformador> listTrafo = tdao.list();
        //GeneradorReportes.getInstance().generarReportes(listCliente, listTrafo);
        sessionExist(request, response);
        fillTables();

        return new ModelAndView (model, REPORTES); // TODO add 'hogares' model
    }

    private static void initModel() {

        model = new ReportesModel();
    }

    public void getReportesPorCliente(Cliente c) {
        List<ReportesTable> tableReporte = new ArrayList<ReportesTable>();
        List<Reporte> reportes = rdao.getReportePorCliente(c.getId());

        model.cleanTableReporte();

        for (Reporte r : reportes) {
            ReportesTable row = new ReportesTable();
            row.setReporte(r);
            tableReporte.add(row);
        }

        model.setTableReporte(tableReporte);
    }

    public void getReportesPorTipo(int type) {
        List<ReportesTable> tableReporte = new ArrayList<ReportesTable>();
        List<Reporte> reportes = rdao.getReportePorCliente(type);

        model.cleanTableReporte();

        for (Reporte r : reportes) {
            ReportesTable row = new ReportesTable();
            row.setReporte(r);
            tableReporte.add(row);
        }

        model.setTableReporte(tableReporte);
    }

    public void getReportesPorTrafo(Transformador trafo) {

        List<ReportesTrafosTable> tableReporte = new ArrayList<ReportesTrafosTable>();
        List<ReporteTransformador> reportes = rdao.getReportePorTransformador(trafo.getId());

        model.cleanTableReporteTrafo();

        for (ReporteTransformador r : reportes) {
            ReportesTrafosTable row = new ReportesTrafosTable();
            row.setReporteTrafo(r);
            tableReporte.add(row);
        }

        model.setTableReporteTrafo(tableReporte);
    }

    private static void fillTables() {

        List<ReportesTable> tableReporte = new ArrayList<ReportesTable>();
        List<ReportesTrafosTable> tableReporteTrafo = new ArrayList<ReportesTrafosTable>();

        List<Reporte> rep = mongo.getReportesClientes();
        List<ReporteTransformador> repT = mongo.getReportesTranformadores();

        for ( Reporte r : rep) {
            ReportesTable row = new ReportesTable();
            r.setClienteName();
            row.setReporte(r);
            tableReporte.add(row);
        }
        for ( ReporteTransformador r : repT) {
            ReportesTrafosTable rowT = new ReportesTrafosTable();
            rowT.setReporteTrafo(r);

            tableReporteTrafo.add(rowT);
        }

        model.setTableReporte(tableReporte);
        model.setTableReporteTrafo(tableReporteTrafo);

    }
}

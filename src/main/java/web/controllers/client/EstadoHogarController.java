package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.dao.BaseDao;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dao.ReglaDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import ar.edu.utn.frba.dds.regla.Regla;
import ar.edu.utn.frba.dds.sensor.Sensor;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.models.AlertModel;
import web.models.EstadoHogarModel;
import web.models.views.EstadoDispositivosTable;
import web.models.views.MedicionesTable;
import web.models.views.ReglaTable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EstadoHogarController extends MainController {
    private static final String ESTADO_HOGAR = "/cliente/estadoHogar.hbs";
    private static EstadoHogarModel model;
    private static AlertModel alert = new AlertModel(false, "", false);
    private static ClientDao cdao = new ClientDao();
    private static DispositivoDao ddao = new DispositivoDao();
    private static ReglaDao rdao = new ReglaDao();
    private static Cliente currentClient;
    private static BaseDao bdao = new BaseDao();

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.homePath(), EstadoHogarController::showHome, engine);
        initModel();
    }

    private static ModelAndView showHome(Request request, Response response) {
        sessionExist(request, response);

        String userSession = request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0, userSession.indexOf("-")));
        currentClient = cdao.getCliente(userID);
        fillReglasTable();
        fillMedicionesTable();
        fillDispositivosTable();
        fillReglasTable();
        //updateDispositivosTable();
        alert.setHideAlert();
        int ultimoConsumo = getUltimoConsumo();
        model.setConsumoUltimo(ultimoConsumo);
        return new ModelAndView(model, ESTADO_HOGAR);
    }

    private static void initModel() {

        model = new EstadoHogarModel();

        //List<HogaresTable> table = new ArrayList<HogaresTable>();
        //List<MedicionesTable> mtable = new ArrayList<MedicionesTable>();
        //List<Cliente> cls = cdao.list();
        //List<Dispositivo> tableDispositivos = new ArrayList<>();
        //tableDispositivos =  ddao.getAllDI(currentClient);
    }

    public static int getUltimoConsumo() {
        int consumo = 0;
        List<DispositivoInteligente> listDI = ddao.getAllDI(currentClient);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < listDI.size(); i++) {
            DispositivoInteligente di = listDI.get(i);
            for (int it = 0; it < di.getConsumos().size(); it++) {
                Consumo c = di.getConsumos().get(it);
                //if (cal.get(Calendar.MONTH) == c.getFechaInicio().getMonth()){
                if (11 == c.getFechaInicio().getMonth()) {
                    consumo += c.getWatts();
                }
            }
        }
        return consumo;
    }

    private static void fillDispositivosTable() {

        List<EstadoDispositivosTable> table = new ArrayList<EstadoDispositivosTable>();
        List<Dispositivo> dispositivos = ddao.getAllDispositivos(currentClient);

        for (Dispositivo d : dispositivos) {
            EstadoDispositivosTable row = new EstadoDispositivosTable();
            row.setDispositivo(d);
            row.setEstado(d.getEstado().name());
            table.add(row);
        }

        model.setTableDispositivos(table);
    }

    private static void fillMedicionesTable() {

        List<MedicionesTable> table = new ArrayList<>();
        List<Dispositivo> dispositivos = ddao.getAllDispositivos(currentClient);

        for (Dispositivo d : dispositivos) {
            if (!(d instanceof Estandard)) {
                if (!((DispositivoInteligente) d).getSensores().isEmpty()) {
                    for (Sensor s : ((DispositivoInteligente) d).getSensores()) {
                        MedicionesTable row = new MedicionesTable();
                        row.setDispositivo(d.getNombre());
                        row.setVariable(s.getClass().getSimpleName());
                        row.setMagnitud(s.getMagnitud().getValor());
                        row.setUnidad(s.getMagnitud().getMagnitud());
                        table.add(row);
                    }
                }
            }
        }

        model.setTableMediciones(table);
    }

    private static void fillReglasTable() {

        List<ReglaTable> table = new ArrayList<>();
        List<Dispositivo> dispositivos = ddao.getAllDispositivos(currentClient);

        for (int it = 0; it < dispositivos.size(); it ++) {
            Dispositivo d = dispositivos.get(it);
            if (!(d instanceof Estandard)) {
                List<Regla> r = rdao.getAllReglas(((DispositivoInteligente) d).getActuador().getId());
                    for(int i = 0; i < r.size(); i++) {
                        Regla regla = r.get(i);
                        regla.ejecutar((DispositivoInteligente) d);
                        ReglaTable row = new ReglaTable();
                        row.setAccion(regla.getMethodName());
                        row.setDispositivo(d.getNombre());
                        table.add(row);
                        bdao.update(d);
                    }
            }
        }
        model.setReglas(table);
    }
}
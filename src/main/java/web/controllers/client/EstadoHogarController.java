package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.ConsumoDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
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
import web.models.views.HogaresTable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EstadoHogarController extends MainController{
    private static final String ESTADO_HOGAR = "/cliente/estadoHogar.hbs";
    private static EstadoHogarModel model;
    private static AlertModel alert = new AlertModel(false,"",false);
    private static ClientDao cdao = new ClientDao();
    private static DispositivoDao ddao = new DispositivoDao();
    private static ConsumoDao codao = new ConsumoDao();
    private static Cliente currentClient;

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.homePath(), EstadoHogarController::showHome, engine);
        initModel();
    }
    private static ModelAndView showHome(Request request, Response response) {
        sessionExist(request,response);

        String userSession =  request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0,userSession.indexOf("-")));
        currentClient = cdao.getCliente(userID);
        fillDispositivosTable();
        //updateDispositivosTable();
        alert.setHideAlert();

        return new ModelAndView(alert, ESTADO_HOGAR);
    }

    private static void initModel() {

        model = new EstadoHogarModel();

        List<HogaresTable> table = new ArrayList<HogaresTable>();
        List<Cliente> cls = cdao.list();
        //List<Dispositivo> tableDispositivos = new ArrayList<>();
        //tableDispositivos =  ddao.getAllDI(currentClient);
    }


    public static int getUltimoConsumo(){
        int consumo = 0;
        List<DispositivoInteligente> listDI = currentClient.getDispositivosInteligentes();
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < listDI.size(); i++){
            DispositivoInteligente di = listDI.get(i);
            for (int it = 0; it < di.getConsumos().size(); it++){
                Consumo c = di.getConsumos().get(it);
                //if (cal.get(Calendar.MONTH) == c.getFechaInicio().getMonth()){
                if (11 == c.getFechaInicio().getMonth()){
                    consumo += c.getWatts();
                }
            }
        }
        return consumo;
    }


    private static void fillDispositivosTable() {

        List<EstadoDispositivosTable> table = new ArrayList<EstadoDispositivosTable>();
        List<Dispositivo> dispositivos = ddao.getAllDI(currentClient);

        for (Dispositivo d : dispositivos) {
            EstadoDispositivosTable row = new EstadoDispositivosTable();
            row.setDispositivo(d);
            row.setEstado(d.getEstado().name());
            table.add(row);
        }

        model.setTableDispositivos(table);
    }


}
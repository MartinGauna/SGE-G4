package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.Usuario;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dao.UsuarioDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.helpers.AdapterSimplex;
import ar.edu.utn.frba.dds.regla.Regla;
import com.github.jknack.handlebars.Handlebars;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.controllers.login.LoginController;
import web.models.AlertModel;
import web.models.SimplexModel;
import web.models.seleccionReglaModel;
import web.models.views.SimplexTable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static web.helper.SessionHelper.setSession;

public class SimplexController extends MainController {
    private static final String SIMPLEX = "/cliente/simplex.hbs";
    private static SimplexModel model;
    private static ClientDao cdao = new ClientDao();
    private static DispositivoDao ddao = new DispositivoDao();
    private static Cliente currentClient;
    private static boolean eficiente = true;
    private static AlertModel alert = new AlertModel(false,"",false);
    private static String simplexAutoB;
    private static String simplexAutoCTA;
    private static Integer simplexAuto;
    private static Integer ultimoConsumoW;

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.simplexPath(), SimplexController::showSimplex, engine);
        Spark.post(Router.simplexPath(),SimplexController::updateSimplex,engine);
        initModel();
    }
    public static ModelAndView updateSimplex(Request request, Response response){

        String userSession = request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0, userSession.indexOf("-")));
        currentClient = cdao.getCliente(userID);
        simplexAuto = currentClient.getSimplex();

        if(simplexAuto == 0){
            currentClient.setSimplex(1);
            simplexAuto = 1;
            cdao.update(currentClient);
            //cdao.addClientIfNotExists(currentClient);
        }
        else {
            currentClient.setSimplex(0);
            simplexAuto = 0;
            cdao.update(currentClient);
            //cdao.addClientIfNotExists(currentClient);
        }

        fillSimplexTable();
        fillModel();

        return new ModelAndView(model,SIMPLEX);
    }

    private static ModelAndView showSimplex(Request request, Response response) {
        sessionExist(request, response);

        String userSession = request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0, userSession.indexOf("-")));
        currentClient = cdao.getCliente(userID);
        ultimoConsumoW = getUltimoConsumo()/1000;

        if(ultimoConsumoW >= 612){eficiente = false;}
        simplexAuto = currentClient.getSimplex();

       try
            {
                alert.setHideAlert();
                fillSimplexTable();
                fillModel();
                return new spark.ModelAndView(model,SIMPLEX);
            }
        catch(Exception e)
            {
                alert.setShowAlertWithMessage("No existe solución dentro de los parámetros (máximo 612kWH).");
                return new ModelAndView(alert, SIMPLEX);
            }

        }

    private static void initModel() {

        model = new SimplexModel();
    }
    private static void fillModel() {
        if(simplexAuto == 0){
            simplexAutoB = "El sistema simplex puede optimizar automaticamente aquellos dispositivos que han consumido más de lo recomendado.";
            simplexAutoCTA = "Activar modo de ahorro simplex";
        }
        else {
            simplexAutoB = "Usted está subscripto al modo ahorro Simplex, aquellos dispositivos que han consumido más de lo eficiente se apagaran automaticamente (chequeo cada 2 minutos).";
            simplexAutoCTA = "Desactivar modo de ahorro simplex";
        }

        model.setUltimoConsumo(ultimoConsumoW);
        model.setEficiente(eficiente);
        model.setSimplexAuto(simplexAutoB);
        model.setSimplexAutoCTA(simplexAutoCTA);
    }

    private static void fillSimplexTable() {
        AdapterSimplex adapterSimplex = new AdapterSimplex();
        List<SimplexTable> table = new ArrayList<SimplexTable>();
        List<Dispositivo> dispositivos = ddao.getAllDispositivos(currentClient);
        double indice = 0;

        adapterSimplex.reporteConsumoEficiente(dispositivos);

       try{
       for(int i = 0;i < adapterSimplex.solucion.getPoint().length;i++)
       {
           indice = (adapterSimplex.solucion.getPoint()[i]);

           SimplexTable row = new SimplexTable();
           row.setDispositivo(dispositivos.get(i).getNombre());
           row.setIndex((int) indice);
           row.setConsumoReal(dispositivos.get(i).getConsumoTotal());
           table.add(row);
       }
       }
       catch(Exception e){}

       model.setSimplexTable(table);
    }

    public static int getUltimoConsumo() {
        int consumo = 0;
        List<DispositivoInteligente> listDI = ddao.getAllDI(currentClient);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < listDI.size(); i++) {
            DispositivoInteligente di = listDI.get(i);
            for (int it = 0; it < di.getConsumos().size(); it++) {
                Consumo c = di.getConsumos().get(it);
                if (11 == c.getFechaInicio().getMonth()) {
                    consumo += c.getWatts();
                }
            }
        }
        return consumo;
    }

}
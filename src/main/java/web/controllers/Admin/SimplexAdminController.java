package web.controllers.Admin;


import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.dao.BaseDao;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.DIFactory;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import ar.edu.utn.frba.dds.exception.IncompleteFormException;
import ar.edu.utn.frba.dds.helpers.BackgroundProcesses;
import ar.edu.utn.frba.dds.sensor.Sensor;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.helper.SessionHelper;
import web.models.AltaDispositivoModel;
import web.models.SimplexAdminModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static ar.edu.utn.frba.dds.Consumo.createConsumo;

public class SimplexAdminController extends MainController {
    private static final String SIMPLEXADMIN = "/admin/simplexAdmin.hbs";

    private static SimplexAdminModel model;
    private static Timer timerlocal;
    private static ClientDao clientDao = new ClientDao();
    private static DispositivoDao dispositivoDao = new DispositivoDao();
    private static BaseDao bdao = new BaseDao();
    private static TimerTask timerTasklocal;
    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.simplexAdminPath(), SimplexAdminController::load,engine);
        Spark.post(Router.simplexAdminPath(),SimplexAdminController::submit,engine);
        initModel();
    }

    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);

        BackgroundProcesses bkg = BackgroundProcesses.getInstance();
        model.setIntervalo(bkg.intervalo);
        if(bkg.prendido){model.setPrendido(true);} else{model.setPrendido(false);}

        return new ModelAndView (model, SIMPLEXADMIN);
    }

    private static void initModel() {
        BackgroundProcesses bkg = BackgroundProcesses.getInstance();
        model = new SimplexAdminModel();
        model.setPrendido(bkg.prendido);
        model.setIntervalo(bkg.intervalo);
    }

    public static ModelAndView submit(Request request, Response response){

        String intervaloQuery = request.queryParams("intervalo");
        String prendido_apagado_string = request.queryParams("prendido_apagado");
        if(request.queryParams("prendido_apagado") == null){prendido_apagado_string = "off";}

        Boolean prendidoQuery = false;
        if (prendido_apagado_string.equals("on")){prendidoQuery = true;}

        BackgroundProcesses bkg = BackgroundProcesses.getInstance();
        bkg.prendido = prendidoQuery;
        bkg.intervalo = Integer.parseInt(intervaloQuery);
        if(bkg.prendido)
        {
           timerlocal = new Timer();
           timerTasklocal = new TimerTask() {
                @Override
                public void run() {
                    List<Cliente>  allClients ;
                    ClientDao cdao = new ClientDao();

                    allClients = cdao.list();
                    for(int i = 0;i < allClients.size();i++)
                    {
                        allClients.get(i).ahorroAutomatico();
                    }
                    //cdao.persistList(allClients);

                }
            };
           timerlocal.schedule(timerTasklocal,0,bkg.intervalo * 1000 * 60);
           model.setPrendido(true);
           model.setIntervalo(bkg.intervalo);
        }
        else
        {
            timerlocal.cancel();
            model.setPrendido(false);
            model.setIntervalo(bkg.intervalo);
        }

        return new ModelAndView (model, SIMPLEXADMIN);
    }





}
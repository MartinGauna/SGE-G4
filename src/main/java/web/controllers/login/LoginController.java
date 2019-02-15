package web.controllers.login;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Usuario;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.UsuarioDao;
import ar.edu.utn.frba.dds.helpers.BackgroundProcesses;
import web.controllers.MainController;
import web.models.AlertModel;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.helper.SessionHelper;


import static web.helper.SessionHelper.setSession;

public class LoginController extends MainController {

    private static final String LOGIN = "/login.hbs";
    private static AlertModel alert = new AlertModel(false,"",false);
    private static Cliente currentClient;
    private static ClientDao cdao = new ClientDao();

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.loginPath(),LoginController::showLogin,engine);
        Spark.post(Router.loginPath(),LoginController::checkLogin,engine);


    }
    private static ModelAndView showLogin(Request request, Response response) {
        if (SessionHelper.existSession(request)){
            response.redirect(Router.homePath());
        }
        alert.setHideAlert();
        return new ModelAndView(alert, LOGIN);
    }

    public static ModelAndView checkLogin(Request request, Response response){

        String email = request.queryParams("email");

        UsuarioDao dao = new UsuarioDao();

        Usuario u =  dao.getUser(email);

        if (u != null && u.getPassword().equals(request.queryParams("password"))){
            alert.setHideAlert();
            setSession(request,u);
            if (dao.isClient(u) != null){
                backgroundProcessInit(request);
                response.redirect(Router.homePath());
            }
            else{
                response.redirect(Router.adminHogaresPath());
            }
        }

        alert.setShowAlertWithMessage("El usuario o password es incorrecto");
        return new ModelAndView(alert,LOGIN);
    }

    public static void backgroundProcessInit(Request request){
        BackgroundProcesses bkg = new BackgroundProcesses();

        String userSession = request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0, userSession.indexOf("-")));
        currentClient = cdao.getCliente(userID);

        bkg.automatizacionAhorroAutomatico(request,1000*90); //1000*30 aprox 20 seg
    }

}

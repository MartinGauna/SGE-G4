package web.controllers.login;

import ar.edu.utn.frba.dds.Usuario;
import ar.edu.utn.frba.dds.dao.UsuarioDao;
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
                response.redirect(Router.homePath());
            }
            else{
                response.redirect(Router.adminHogaresPath());
            }
        }

        alert.setShowAlertWithMessage("El usuario o password es incorrecto");
        return new ModelAndView(alert,LOGIN);
    }

}

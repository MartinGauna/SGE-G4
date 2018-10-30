package web.controllers.login;

import ar.edu.utn.frba.dds.Usuario;
import ar.edu.utn.frba.dds.dao.UsuarioDao;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.models.LoginModel;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private static final String LOGIN = "/login.hbs";
    private static LoginModel model;

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.loginPath(),LoginController::showLogin,engine);
        Spark.post(Router.loginPath(),LoginController::checkLogin,engine);

    }
    private static ModelAndView showLogin(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        model.put("message", "Hello Handlebars!");

        return new ModelAndView(model, LOGIN); // located in resources/templates
    }

    public static ModelAndView checkLogin(Request request, Response response){

        String email = request.queryParams("email");

        UsuarioDao dao = new UsuarioDao();

        Usuario u =  dao.getUser(email);

        if (u != null && u.getPassword().equals(request.queryParams("password"))){

            if (dao.isClient(u) != null)
            {response.redirect(Router.homePath());}
            else
            {response.redirect(Router.adminHogaresPath());}
        }

        response.status(403);

        return  new ModelAndView("Usuario o password incorrecto",LOGIN);
    }

    private static void initModel() {
        model = new LoginModel();
    }




}

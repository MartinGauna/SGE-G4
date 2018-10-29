package web.controllers.login;

import com.github.jknack.handlebars.Handlebars;
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
        Spark.get(Router.loginPath(), LoginController::showLogin,engine);
        initModel();
        Handlebars handlebars = new Handlebars();

        //handlebars.registerHelper("signinact",helper)

    }
    private static ModelAndView showLogin(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        model.put("message", "Hello Handlebars!");

        return new ModelAndView(model, LOGIN); // located in resources/templates
    }

    private static void initModel() {

        model = new LoginModel();
        //model.setTable();

    }


}

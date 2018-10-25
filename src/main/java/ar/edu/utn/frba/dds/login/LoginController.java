package ar.edu.utn.frba.dds.login;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    public static Route serveLoginPage = (Request request, Response response) -> {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

        Map<String, Object> model = new HashMap<>();

    return engine.render(new ModelAndView(model, "templates/login.html"));

    };
}

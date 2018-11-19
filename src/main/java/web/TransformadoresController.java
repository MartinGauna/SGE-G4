package web;

import org.apache.commons.lang3.ObjectUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.controllers.MainController;

public class TransformadoresController extends MainController {
    private static final String Template = "/transformadores.hbs";

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.transformadoresPath(), TransformadoresController::showTransformadores,engine);
        initModel();
    }

    private static ModelAndView showTransformadores(Request request, Response response) {

        return new ModelAndView (null, Template);
    }

    private static void initModel() {

    }
}

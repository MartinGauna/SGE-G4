package web;

import ar.edu.utn.frba.dds.dao.TransformadorDao;
import org.apache.commons.lang3.ObjectUtils;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.controllers.MainController;
import web.models.TransformadoresModel;

public class TransformadoresController extends MainController {
    private static final String Template = "/transformadores.hbs";
    private static TransformadoresModel model;
    private static TransformadorDao tdao = new TransformadorDao();

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.transformadoresPath(), TransformadoresController::showTransformadores,engine);
        Spark.get(Router.transformadoresJsonPath(), (request, response) -> {
            response.type("application/json");
            response.status(200);
//            JSONObject res = new JSONObject();
//            res.put("data", "someData");

            return model.getTransformadoresJson();
//            return "{'key':'STRING!!!!'}";
        });
        initModel();
    }

    private static ModelAndView showTransformadores(Request request, Response response) {

        return new ModelAndView (model, Template);
    }

    private static void initModel() {
        model = new TransformadoresModel(tdao.list());
    }

}

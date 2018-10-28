package web.controllers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import web.Router;
import web.helper.SessionHelper;

public abstract class mainController {

    protected static void sessionExist (Request request, Response response) {
//        if (!SessionHelper.existSession(request)){
//            response.redirect(Router.loginPath());
//        }
    }
}

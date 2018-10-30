package web.controllers;

import spark.Spark;
import web.Router;
import web.helper.SessionHelper;

public class LogoutController {

    public static void init(){
        Spark.get(Router.logoutPath(),((request, response) -> {
            SessionHelper.deleteSession(request);
            response.redirect(Router.loginPath());
            return null;
        }));
    }

}
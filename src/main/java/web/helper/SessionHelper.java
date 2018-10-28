package web.helper;

import ar.edu.utn.frba.dds.Usuario;
import spark.Request;

public class SessionHelper {

    private static final String SESSION = "user";

    private static SessionHelper ourInstance = new SessionHelper();

    public static SessionHelper getInstance() {
        return ourInstance;
    }

    private SessionHelper() {
    }

    public static void setSession(Request request, Usuario user){
        request.session().attribute(SESSION,user.userSession());
    }

    public static Boolean existSession(Request request){
        return request.session().attribute(SESSION) != null;
    }
}

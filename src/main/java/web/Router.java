package web;

/**
 * Created by martin.gauna on 27/10/18.
 */
public class Router {

    private static final String LOGIN = "/login";
    private static final String HOME = "/home";
    private static final String LOGOUT = "/logout";
    private static final String ADMIN_HOGARES = "/hogares";




    public static String loginPath(){
        return LOGIN;
    }

    public static String homePath(){
        return HOME;
    }

    public static String logoutPath(){
        return LOGOUT;
    }

    public static String adminHogaresPath() { return ADMIN_HOGARES; }

//    public static String hogares() { return ADMIN_HOGARES; }
//    public static String hogares() { return ADMIN_HOGARES; }
//    public static String hogares() { return ADMIN_HOGARES; }
//    public static String hogares() { return ADMIN_HOGARES; }


}

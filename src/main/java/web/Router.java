package web;

/**
 * Created by martin.gauna on 27/10/18.
 */
public class Router {

    private static final String LOGIN = "/login";
    private static final String HOME = "/home";
    private static final String LOGOUT = "/logout";
    private static final String ADMIN_HOGARES = "/hogares";
    private static final String ALTA_DISPOSITIVO = "/altaDispositivo";
    private static final String REPORTES = "/reportes";
    private static final String UPLOAD = "/upload";


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

    public static String altaDispositivoPath() { return ALTA_DISPOSITIVO; }

    public static String reportesPath() { return REPORTES; }

    public static String uploadPath(){
        return UPLOAD;
    }

//    public static String hogares() { return ADMIN_HOGARES; }
//    public static String hogares() { return ADMIN_HOGARES; }
//    public static String hogares() { return ADMIN_HOGARES; }
//    public static String hogares() { return ADMIN_HOGARES; }


}

package web;

/**
 * Created by martin.gauna on 27/10/18.
 */
public class Router {

    private static final String LOGIN = "/login";
    private static final String HOME = "/estadoHogar";
    private static final String LOGOUT = "/logout";
    private static final String ADMIN_HOGARES = "/hogares";
    private static final String ALTA_DISPOSITIVO = "/altaDispositivo";
    private static final String ALTA_DISPOSITIVO_CLIENTE = "/altaDispositivoCliente";
    private static final String REPORTES = "/reportes";
    private static final String TRANSFORMADORES = "/transformadores";
    private static final String TRANSFORMADORESJSON = "/transformadores/json";
    private static final String UPLOAD = "/uploadFile";


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

    public static String altaDispositivoClientePath() { return ALTA_DISPOSITIVO_CLIENTE; }

    public static String reportesPath() { return REPORTES; }

    public static String transformadoresPath() { return TRANSFORMADORES; }
    public static String transformadoresJsonPath() { return TRANSFORMADORESJSON; }

    public static String uploadPath(){ return UPLOAD; }

}

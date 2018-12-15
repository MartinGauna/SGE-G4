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
    private static final String ALTA_REGLAS = "/altaReglas";
    private static final String REGLAS = "/reglas";
    private static final String REPORTES = "/reportes";
    private static final String TRANSFORMADORES = "/transformadores";
    private static final String TRANSFORMADORESJSON = "/transformadores/json";
    private static final String UPLOAD = "/uploadFile";
    private static final String ESTADO_DISPOSITIVOS = "/estadosDispositivos";
    private static final String GENERAR_REPORTE = "/generarReporte";
    private static final String GENERAR_REPORTE_CLIENTES = "/generarReporteClientes";
    private static final String GENERAR_REPORTE_TRANSFORMADOR = "/generarReporteTransformador";
    private static final String BAJA_MODIFICACION_DISPOSITIVO = "/seleccionDispositivo";
    private static final String BAJA_MODIFICACION_REGLA = "/seleccionRegla";
    private static final String BAJA_DISPOSITIVO = "/seleccionDispositivo/:id";
    private static final String MODIFICAR_DISPOSITIVO = "/dispositivo/modificar";
    private static final String BAJA_REGLA = "/regla/delete";
    private static final String MODIFICAR_REGLA = "/regla/modificar";

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

    public static String altaReglasPath() { return ALTA_REGLAS; }

    public static String altaDispositivoClientePath() { return ALTA_DISPOSITIVO_CLIENTE; }

    public static String reportesPath() { return REPORTES; }

    public static String reglasPath() { return REGLAS; }

    public static String transformadoresPath() { return TRANSFORMADORES; }

    public static String transformadoresJsonPath() { return TRANSFORMADORESJSON; }

    public static String uploadPath(){ return UPLOAD; }

    public static String estadosDispositivosPath(){ return ESTADO_DISPOSITIVOS; }

    public static String generarReportePath(){ return GENERAR_REPORTE; }

    public static String generarReporteClientesPath(){ return GENERAR_REPORTE_CLIENTES; }

    public static String generarReporteTransformadorPath(){ return GENERAR_REPORTE_TRANSFORMADOR; }

    public static String bajaModificacionDispositivoPath(){ return BAJA_MODIFICACION_DISPOSITIVO; }

    public static String bajaModificacionReglaPath(){ return BAJA_MODIFICACION_REGLA; }

    public static String bajaDispositivoPath(){ return BAJA_DISPOSITIVO; }

    public static String modificarDispositivoPath(){ return MODIFICAR_DISPOSITIVO; }

    public static String modificarReglaPath(){ return MODIFICAR_REGLA; }

    public static String bajaReglaPath(){ return BAJA_REGLA; }
}

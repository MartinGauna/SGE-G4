package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.BaseDao;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dao.ReglaDao;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.regla.Regla;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.models.seleccionReglaModel;
import web.models.views.ReglaPullDown;

import java.util.ArrayList;
import java.util.List;

public class SeleccionReglaController extends MainController {

    private static final String SELECCION_REGLA = "/cliente/seleccionRegla.hbs";
    private static seleccionReglaModel model;
    private static Cliente cliente;
    private static ClientDao cdao = new ClientDao();
    private static DispositivoDao ddao = new DispositivoDao();
    private static ReglaDao rdao = new ReglaDao();
    private static BaseDao bdao = new BaseDao();


    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get(Router.bajaModificacionReglaPath(), SeleccionReglaController::load, engine);
        Spark.put(Router.modificarReglaPath(), SeleccionReglaController::modificar, engine);
        Spark.delete(Router.bajaReglaPath(), SeleccionReglaController::delete, engine);
        initModel();
    }

    private static ModelAndView load(Request request, Response response) {
        sessionExist(request, response);
        getCurrentClient(request);
        model.setShowAlert(false);
        model.getReglas().clear();
        fillReglas();

        return new ModelAndView(model, SELECCION_REGLA);
    }

    private static void initModel() {
        model = new seleccionReglaModel();
    }


    public static ModelAndView delete(Request request, Response response) {

       //datos del form:
        int reglaID = Integer.parseInt(request.params(":id"));
        Regla r = rdao.getReglaByID(reglaID);
        List<Object> toDelete = new ArrayList<>();

        toDelete.add(r);
        bdao.deleteList(toDelete);

        model.success("La regla fue eliminada exitosamente");
        return new ModelAndView( updateModel(), SELECCION_REGLA);
    }

    private static void getCurrentClient(Request request) {
        String userSession = request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0, userSession.indexOf("-")));
        cliente = cdao.getCliente(userID);
    }

    public static ModelAndView modificar(Request request, Response response) {
        //todo
        System.out.println(request.body());
        String[] parameters = request.body().split("&");

        int reglaID = Integer.parseInt((parameters[0].split("="))[1]);
        String dispositivo = (parameters[1].split("="))[1];
        String methodname = (parameters[2].split("="))[1];

        Regla r = rdao.getReglaByID(reglaID);





        model.success("La regla fue modificado exitosamente");
        return new ModelAndView( updateModel(), SELECCION_REGLA);
    }

    private static seleccionReglaModel updateModel(){
        model.getReglas().clear();
        fillReglas();
        model.success("La regla fue eliminada exitosamente");
        return model;
    }

    private static void fillReglas() {

        List<Regla> r = rdao.list();
        for(int i = 0; i < r.size(); i++){
            Regla regla = r.get(i);
            regla.ejecutar();
            ReglaPullDown row = new ReglaPullDown();
            row.setId(regla.getId());
            row.setAccion(regla.getMethodName());
            row.setDispositivo(regla.getActuador().getDispositivo().getNombre());
            model.getReglas().add(row);
        }
    }
}
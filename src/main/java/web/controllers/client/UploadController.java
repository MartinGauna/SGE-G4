package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.DIFactory;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.exception.InvalidFileFormatException;
import ar.edu.utn.frba.dds.exception.ParserErrorException;
import ar.edu.utn.frba.dds.jsonParser.JsonParser;
import ar.edu.utn.frba.dds.utils.FileUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;

import web.helper.AlertHelper;
import web.models.AlertModel;
import web.controllers.MainController;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

public class UploadController extends MainController {

    public static final String HTML = "/cliente/uploadFile.hbs";
    private static MultipartConfigElement multipartConfigElement;
    private static DispositivoDao ddao;
    private static ClientDao cdao;
    DIFactory fm = new DIFactory();
    private static Cliente currentClient;


    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        setupMultipleElementConfig();
        Spark.get(Router.uploadPath(),UploadController::load,engine);
        Spark.post(Router.uploadPath(),UploadController::upload,engine);
    }

    private static ModelAndView load (Request request, Response response){
        sessionExist(request, response);
        return new ModelAndView(AlertHelper.none(),HTML);
    }

    public static ModelAndView upload (Request request, Response response){
        cdao = new ClientDao();
        ddao = new DispositivoDao();
        AlertModel alertModel;
        JsonParser jp = new JsonParser();
        String tipo = request.queryParams("tipo");
        alertModel = null;
        getCurrentClient(request);

        /*
        * TODO: en el hbs el "form" tiene la siguiente estructura:
        * <form class="form-label-group" action="/uploadFile" method="POST" enctype="multipart/form-data">
        * el "enctype" tiene ese valor para que pueda enviarse en el request el archivo subido.
        * pero ademas de esto necesito que en el request se envie el valor del radiobutton seleccionado,
        * pero llega como null.
        * (se probó sacar el "enctype". Si lo saco, llega el valor del radiobutton y no el archivo.
        * basicamente si viaja una cosa no viaja la otra)
        *
        * Habria que encontrar la forma de que en el mismo request se envien ambas cosas.
        * posible solucion:hacer una funcion de jquery o algo por el estilo
        * que arme un request concatenando ambas cosas
        * */



        try {
            request.raw().setAttribute("org.eclipse.jetty.multipartConfig",multipartConfigElement);
            File f = FileUtils.getFileWithPath(request.raw().getPart("file"));

            if(tipo.equals("DispositivoInteligente")) {
                jp.loadDIFromFile(f);
            } else {
                jp.loadDEFromFile(f);
            }
            alertModel = AlertHelper.success();

        }catch (InvalidFileFormatException ex){
            alertModel = AlertHelper.failed(ex.getMessage());
        }catch (ParserErrorException e){
            alertModel = AlertHelper.failed();
        } catch (ServletException e) {
            alertModel = AlertHelper.failed(e.getMessage());
        } catch (IOException e) {
            alertModel = AlertHelper.failed(e.getMessage());
        }

        return new ModelAndView(alertModel,HTML);
    }

    private static void setupMultipleElementConfig(){
        multipartConfigElement = new MultipartConfigElement(
                "/tmp",100000000 , 100000000, 1024);
    }

    private static void getCurrentClient(Request request) {
        String userSession =  request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0,userSession.indexOf("-")));
        currentClient = cdao.getCliente(userID);
    }

}
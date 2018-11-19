package web.controllers.client;

import ar.edu.utn.frba.dds.exception.InvalidFileFormatException;
import ar.edu.utn.frba.dds.exception.ParserErrorException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;

import web.helper.AlertHelper;
import web.models.AlertModel;

import web.controllers.MainController;


public class UploadController extends MainController {

    public static final String HTML = "/cliente/uploadFile.hbs";

    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

        Spark.get(Router.uploadPath(),UploadController::load,engine);
        Spark.post(Router.uploadPath(),UploadController::upload,engine);
    }

    private static ModelAndView load (Request request, Response response){
        sessionExist(request, response);
        return new ModelAndView(AlertHelper.none(),HTML);
    }

    public static ModelAndView upload (Request request, Response response){
        AlertModel alertModel;

        try {
            alertModel = null;
            //TODO parseo json

        }catch (InvalidFileFormatException ex){
            alertModel = AlertHelper.failed(ex.getMessage());
        }catch (ParserErrorException e){
            alertModel = AlertHelper.failed();
        }
        return new ModelAndView(alertModel,HTML);
    }
}
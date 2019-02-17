package web.controllers.client;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.dao.BaseDao;
import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.DIFactory;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import ar.edu.utn.frba.dds.exception.InvalidFileFormatException;
import ar.edu.utn.frba.dds.exception.ParserErrorException;
import ar.edu.utn.frba.dds.jsonParser.JsonParser;
import ar.edu.utn.frba.dds.sensor.Sensor;
import ar.edu.utn.frba.dds.utils.FileUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.Router;
import web.controllers.MainController;
import web.helper.AlertHelper;
import web.models.AlertModel;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static ar.edu.utn.frba.dds.Consumo.createConsumo;

@WebServlet(urlPatterns = {"/uploadFile/*"})
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UploadController extends MainController {

    public static final String HTML = "/cliente/uploadFile.hbs";
    private static MultipartConfigElement multipartConfigElement;
    private static DispositivoDao ddao;
    private static ClientDao cdao;
    DIFactory fm = new DIFactory();
    private static Cliente currentClient;
    private static BaseDao bdao = new BaseDao();


    public static void init() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        AlertModel alertModel = null;
        cdao = new ClientDao();
        ddao = new DispositivoDao();
        setupMultipleElementConfig();
        Spark.get(Router.uploadPath(),UploadController::load,engine);
        //Spark.post(Router.uploadPath(),UploadController::upload,engine);
        JsonParser jp = new JsonParser();
        Spark.post("/uploadFile", (req, res) -> {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("D:/tmp"));
            getCurrentClient(req);
            Part filePartInteligente = req.raw().getPart("inputFileInteligente");
            Part filePartEstandard = req.raw().getPart("inputFileStandard");
            if(filePartInteligente.getSize() > 0)
            {
                try (InputStream inputStream = filePartInteligente.getInputStream()) {
                    File fInteligente = FileUtils.getFileWithPath(filePartInteligente);
                    List<Dispositivo> dispositivoInteligenteList = null;
                    DispositivoDao dispositivoDao = new DispositivoDao();
                    dispositivoInteligenteList = jp.loadDIFromFile(fInteligente, currentClient);
                    Dispositivo d = null;
                    Consumo cons = null;
                    for (int i = 0; i < dispositivoInteligenteList.size(); i++) {
                    //dispositivoInteligenteList.forEach(d -> dispositivoDao.addDispositivoIfNotExists(d));
                        //dispositivoDao.addDispositivoIfNotExists(dispositivoInteligenteList.get(i));
                        List<Object> topersist = new ArrayList<>();
                        d = dispositivoInteligenteList.get(i);
                        if (!((DispositivoInteligente)d).getSensores().isEmpty()) {
                            for (Sensor s : ((DispositivoInteligente)d).getSensores()) {
                                topersist.add(s.getMagnitud());
                                topersist.add(s);
                            }
                        }
                        cons = createConsumo(d);
                        topersist.add(d);
                        topersist.add(cons);
                        bdao.persistList(topersist);
                    }
                    //alertModel.setIsSuccess(true);
                } catch (IOException e) {
                 e.printStackTrace();
                }
             }
            if(filePartEstandard.getSize() > 0)
            {   try (InputStream inputStream = filePartEstandard.getInputStream()) {
                File fEstandar = FileUtils.getFileWithPath(filePartEstandard);
                List<Estandard> dispositivoEstandard = null;
                DispositivoDao dispositivoDao = new DispositivoDao();
                dispositivoEstandard = jp.loadDEFromFile(fEstandar);
                dispositivoEstandard.forEach(d -> dispositivoDao.addDispositivoIfNotExists(d));
                //alertModel = AlertHelper.success();
            } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return new ModelAndView(alertModel,HTML);
        });

    }


    private static ModelAndView load (Request request, Response response){
        sessionExist(request, response);
        return new ModelAndView(AlertHelper.none(),HTML);
    }

    public static ModelAndView upload (Request request, Response response) throws ServletException, IOException{

        AlertModel alertModel;
        JsonParser jp = new JsonParser();
        //Part tipoPart = request.raw().getPart("tipo");
        //String tipo = tipoPart.toString();
        //String tipo = request.queryParams("tipo");

        alertModel = null;
        //getCurrentClient(request);

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
            //request.raw().setAttribute("org.eclipse.jetty.multipartConfig",multipartConfigElement);
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("C:/tmp"));
            File fEstandard = FileUtils.getFileWithPath(request.raw().getPart("inputFileStandard"));
            File fInteligente = FileUtils.getFileWithPath(request.raw().getPart("inputFileInteligente"));

            if(fEstandard.exists()) {jp.loadDEFromFile(fEstandard);}
            if(fInteligente.exists()){jp.loadDEFromFile(fInteligente);}
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
       multipartConfigElement = new MultipartConfigElement("/temps",100000000 , 100000000, 1024);
    }

    private static void getCurrentClient(Request request) {
        String userSession =  request.session().attribute("user");
        Integer userID = Integer.parseInt(userSession.substring(0,userSession.indexOf("-")));
        currentClient = cdao.getCliente(userID);
    }

}
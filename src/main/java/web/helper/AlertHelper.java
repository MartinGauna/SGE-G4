package web.helper;

import web.models.AlertModel;

public class AlertHelper {

    public static AlertModel success(String message){
        AlertModel a = alert(message);
        a.setIsSuccess(true);
        return a;
    }

    public static AlertModel success(){
        return success("El archivo subio con exito");
    }

    public static AlertModel failed(){
        return failed("Hubo un error. Intente mas tarde");
    }

    public static AlertModel failed(String message){
        AlertModel a = alert(message);
        a.setIsSuccess(false);
        return a;
    }

    private static AlertModel alert(String message){
        AlertModel a = new AlertModel();
        a.setShowAlert(true);
        a.setMessage(message);
        return a;
    }

    public static AlertModel none(){
        AlertModel alertModel = success();
        alertModel.setShowAlert(false);
        return alertModel;
    }


}

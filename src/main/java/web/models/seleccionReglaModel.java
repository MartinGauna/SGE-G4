package web.models;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import web.models.views.ReglaPullDown;

import java.util.ArrayList;
import java.util.List;


public class seleccionReglaModel {

    private List<ReglaPullDown> reglas;
    private List<Dispositivo> dispositivos;
    private Boolean isSuccess;
    private String message;
    private boolean showAlert;

    public seleccionReglaModel() {
        reglas = new ArrayList<>();
    }

    public boolean isShowAlert() {
        return showAlert;
    }

    public void setShowAlert(boolean showAlert) {
        this.showAlert = showAlert;
    }

    public List<ReglaPullDown> getReglas() {
        return reglas;
    }

    public void setReglas(List<ReglaPullDown> reglas) {
        this.reglas = reglas;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean type) {
        this.isSuccess = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHideAlert(){
        setShowAlert(false);
    }


    public void success(String message) {
        this.setShowAlert(true);
        this.setIsSuccess(true);
        this.setMessage(message);
    }

    public void failed(String message){
        this.setIsSuccess(false);
        this.setMessage(message);
    }


}


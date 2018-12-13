package web.models;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import web.models.views.ReglaTable;
import java.util.List;


public class ReglasModel {

    private ReglaTable reglasDisposibles;
    private List<ReglaTable> table;
    private Boolean isSuccess;
    private String message;
    private boolean showAlert;

    public void setTable(List<ReglaTable> table) {
        this.table = table;
    }

    public ReglasModel() {
        reglasDisposibles = new ReglaTable();

    }

    public boolean isShowAlert() {
        return showAlert;
    }

    public void setShowAlert(boolean showAlert) {
        this.showAlert = showAlert;
    }

    public List<Dispositivo> getDispositivos() {
        return this.getDispositivos();
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


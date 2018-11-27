package web.models;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import web.models.views.ReglaTable;

import java.util.ArrayList;
import java.util.List;


public class ReglasModel {

    private ReglaTable reglasDisposibles;
    private List<ReglaTable> table;
    private List<Cliente> clientes;
    private Boolean isSuccess;
    private String message;
    private boolean showAlert;
    public void setTable(List<ReglaTable> table) {
        this.table = table;
    }

    public ReglasModel() {
        reglasDisposibles = new ReglaTable();
        clientes = new ArrayList<>();

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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
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


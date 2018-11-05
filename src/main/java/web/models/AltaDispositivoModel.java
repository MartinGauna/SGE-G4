package web.models;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dispositivo.*;
import web.models.views.DispositivosTable;

import java.util.*;


public class AltaDispositivoModel {

    private DispositivosTable dispositivosDisponibles;
    private List<Cliente> clientes;
    private Boolean isSuccess;
    private String message;
    private boolean showAlert;


    public AltaDispositivoModel() {
        dispositivosDisponibles = new DispositivosTable();
        clientes = new ArrayList<>();
    }

    public boolean isShowAlert() {
        return showAlert;
    }

    public void setShowAlert(boolean showAlert) {
        this.showAlert = showAlert;
    }

    public List<Dispositivo> getDispositivos() {
        return this.dispositivosDisponibles.getDispositivosDisponibles();
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


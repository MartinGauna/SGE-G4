package web.models;

import ar.edu.utn.frba.dds.Cliente;

import java.util.ArrayList;
import java.util.List;

public class AltaReporteModel {

    private List<Cliente> clientes;
    private String tipoReportefechainicio;
    private String fechafin;
    private Boolean isSuccess;
    private String message;
    private boolean showAlert;


    public AltaReporteModel(String tipoReportefechainicio, String fechafin) {
        this.clientes = new ArrayList<>();
        this.tipoReportefechainicio = tipoReportefechainicio;
        this.fechafin = fechafin;
    }

    public AltaReporteModel() {
        this.clientes = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getTipoReportefechainicio() {
        return tipoReportefechainicio;
    }

    public void setTipoReportefechainicio(String tipoReportefechainicio) {
        this.tipoReportefechainicio = tipoReportefechainicio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    public boolean isShowAlert() {
        return showAlert;
    }

    public void setShowAlert(boolean showAlert) {
        this.showAlert = showAlert;
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

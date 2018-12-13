package web.models;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Transformador;

import java.util.ArrayList;
import java.util.List;

public class AltaReporteModel {

    private List<Cliente> clientes;
    private List<Transformador> transformadores;
    private String tipoReportefechainicio;
    private String fechafin;
    private Boolean isSuccess1;
    private Boolean isSuccess2;
    private String message;
    private boolean showAlert1;
    private boolean showAlert2;

    public AltaReporteModel(String tipoReportefechainicio, String fechafin) {
        this.clientes = new ArrayList<>();
        this.transformadores = new ArrayList<>();
        this.tipoReportefechainicio = tipoReportefechainicio;
        this.fechafin = fechafin;
    }

    public AltaReporteModel() {
        this.clientes = new ArrayList<>();
        this.transformadores = new ArrayList<>();
    }

    public List<Transformador> getTransformadores() {
        return transformadores;
    }

    public void setTransformadores(List<Transformador> transformadores) {
        this.transformadores = transformadores;
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

    public boolean isShowAlert1() {
        return showAlert1;
    }

    public void setShowAlert1(boolean showAlert) {
        this.showAlert1 = showAlert;
    }

    public void setShowAlert2(boolean showAlert) {
        this.showAlert2 = showAlert;
    }

    public Boolean getIsSuccess1() {
        return isSuccess1;
    }

    public Boolean getIsSuccess2() {
        return isSuccess2;
    }

    public void setIsSuccess1(Boolean type) {
        this.isSuccess1 = type;
    }

    public void setIsSuccess2(Boolean type) {
        this.isSuccess2 = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHideAlert1(){
        setShowAlert1(false);
    }

    public void success1(String message) {
        this.setShowAlert1(true);
        this.setShowAlert2(false);
        this.setIsSuccess1(true);
        this.setIsSuccess2(false);
        this.setMessage(message);
    }

    public void setHideAlert2(){
        setShowAlert2(false);
    }

    public void success2(String message) {
        this.setShowAlert1(false);
        this.setShowAlert2(true);
        this.setIsSuccess1(false);
        this.setIsSuccess2(true);
        this.setMessage(message);
    }

    public void failed(String message){
        this.setIsSuccess1(false);
        this.setIsSuccess2(false);
        this.setMessage(message);
    }
}

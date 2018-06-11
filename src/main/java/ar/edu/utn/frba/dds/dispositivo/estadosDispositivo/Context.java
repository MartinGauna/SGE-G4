package ar.edu.utn.frba.dds.dispositivo.estadosDispositivo;

import java.util.NoSuchElementException;

public class Context {

    private DIState status;

    public Context(String status) {
        switch (status) {
            case "activo":
                setStatus(new Activo());
                break;
            case "apagado":
                setStatus(new Apagado());
                break;
            case "ahorro":
                setStatus(new Ahorro());
                break;
            default: throw new NoSuchElementException("estado no implementado");
        }
    }

    public void setStatus(DIState status) { this.status = status;
    }

    public DIState getStatus() {return status;}

    public String getEstado() {
        if(status instanceof Activo){return "Activo";}
        if(status instanceof Ahorro){return "Ahorro";}
        if(status instanceof Apagado){return "Apagado";}
        else{return "Estado no encontrado.";}
    }

    public void handleActivo() {
        setStatus(new Activo());
    }

    public void handleApagado() {
        setStatus(new Apagado());
    }

    public void handleAhorro() {
        setStatus(new Ahorro());
    }
}

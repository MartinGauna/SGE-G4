package ar.edu.utn.frba.dds.actuador;

public interface Actuador {

    public void prenderDispositivo();
    public void apagarDispositivo();

    public void cambiarModoAAhorro();
    public void cambiarModoAPrendido();
    public void cambiarModoAApagado();

    //configurar timer?
    //agendar accion?
}

package ar.edu.utn.frba.dds.actuador;

public abstract class Actuador {

    public abstract void prenderDispositivo();
    public abstract void apagarDispositivo();

    public abstract void cambiarModoAAhorro();
    public abstract void cambiarModoAPrendido();
    public abstract void cambiarModoAApagado();

}

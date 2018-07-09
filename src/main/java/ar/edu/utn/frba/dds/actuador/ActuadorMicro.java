package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteMicro;

public class ActuadorMicro implements Actuador {

    private DispositivoInteligenteMicro micro;

    public ActuadorMicro(DispositivoInteligenteMicro micro) {
        this.micro = micro;
    }

    public void prenderDispositivo() {micro.encender();
    }
    public void apagarDispositivo() {
        micro.apagar();
    }

    public void cambiarModoAAhorro() {
        micro.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        micro.encender();
    }
    public void cambiarModoAApagado() {
        micro.apagar();
    }

}

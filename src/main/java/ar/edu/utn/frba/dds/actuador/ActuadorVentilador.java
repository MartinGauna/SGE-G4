package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteVentilador;

public class ActuadorVentilador extends Actuador {

    private DispositivoInteligenteVentilador ventilador;

    public ActuadorVentilador(DispositivoInteligenteVentilador ventilador) {
        this.ventilador = ventilador;
    }

    public void prenderDispositivo() {ventilador.encender();
    }
    public void apagarDispositivo() {
        ventilador.apagar();
    }

    public void cambiarModoAAhorro() {
        ventilador.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        ventilador.encender();
    }
    public void cambiarModoAApagado() {
        ventilador.apagar();
    }

}

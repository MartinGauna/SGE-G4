package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligentePC;

public class ActuadorPC extends Actuador {

    private DispositivoInteligentePC pc;

    public ActuadorPC(DispositivoInteligentePC pc) {
        this.pc = pc;
    }

    public void prenderDispositivo() {pc.encender();
    }
    public void apagarDispositivo() {
        pc.apagar();
    }

    public void cambiarModoAAhorro() {
        pc.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        pc.encender();
    }
    public void cambiarModoAApagado() {
        pc.apagar();
    }

}

package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteLavarropas;

public class ActuadorLavarropas extends Actuador {

    private DispositivoInteligenteLavarropas lr;

    public ActuadorLavarropas(DispositivoInteligenteLavarropas lr) {
        this.lr = lr;
    }

    public void prenderDispositivo() {lr.encender();
    }
    public void apagarDispositivo() {
        lr.apagar();
    }

    public void cambiarModoAAhorro() {
        lr.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        lr.encender();
    }
    public void cambiarModoAApagado() {
        lr.apagar();
    }

}

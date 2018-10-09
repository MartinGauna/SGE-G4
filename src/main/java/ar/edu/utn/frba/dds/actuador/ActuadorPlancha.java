package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligentePlancha;

public class ActuadorPlancha extends Actuador {

    private DispositivoInteligentePlancha plancha;

    public ActuadorPlancha(DispositivoInteligentePlancha plancha) {
        this.plancha = plancha;
    }

    public void prenderDispositivo() {plancha.encender();
    }
    public void apagarDispositivo() {
        plancha.apagar();
    }

    public void cambiarModoAAhorro() {
        plancha.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        plancha.encender();
    }
    public void cambiarModoAApagado() {
        plancha.apagar();
    }

}

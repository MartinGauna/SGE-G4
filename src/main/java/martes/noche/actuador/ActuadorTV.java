package martes.noche.actuador;

import martes.noche.dispositivo.DispositivoInteligenteTV;

public class ActuadorTV implements Actuador {

    private DispositivoInteligenteTV tv;

    public ActuadorTV(DispositivoInteligenteTV tv) {
        this.tv = tv;
    }

    public void prenderDispositivo() {
        tv.encender();
    }
    public void apagarDispositivo() {
        tv.apagar();
    }

    public void cambiarModoAAhorro() {
        tv.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        tv.encender();
    }
    public void cambiarModoAApagado() {
        tv.apagar();
    }

    public void cambiarACanal(int canal) {
        tv.setCanal(canal);
    }

    public void subirCanal() {
        tv.setCanal(tv.getCanal() + 1);
    }

    public void bajarCanal() {
        tv.setCanal(tv.getCanal() - 1);
    }

    public void subirVolumen() {
        tv.setVolumen(tv.getVolumen() + 1);
    }

    public void bajarVolumen() {
        tv.setVolumen(tv.getVolumen() - 1);
    }
}

package martes.noche.actuador;

import martes.noche.dispositivo.DispositivoInteligenteHeladera;

public class ActuadorHeladera implements Actuador {
    private DispositivoInteligenteHeladera heladera;

    public ActuadorHeladera(DispositivoInteligenteHeladera heladera) {
        this.heladera = heladera;
    }

    public void prenderDispositivo() {
        heladera.encender();
    }
    public void apagarDispositivo() {
        heladera.apagar();
    }

    public void cambiarModoAAhorro() {
        heladera.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        heladera.encender();
    }
    public void cambiarModoAApagado() {
        heladera.apagar();
    }

    public void subirIntensidad(int cantidad) {
        heladera.setIntensidad(heladera.getIntensidad() + cantidad);
    }

    public void bajarIntensidad(int cantidad) {
        heladera.setIntensidad(heladera.getIntensidad() - cantidad);
    }
}

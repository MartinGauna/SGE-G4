package martes.noche;

public class ActuadorAAcondicionado implements Actuador {

    private DispositivoInteligenteAAcondicionado aire;

    public ActuadorAAcondicionado(DispositivoInteligenteAAcondicionado aire) {
        this.aire = aire;
    }

    public void prenderDispositivo() {
        aire.encender();
    }

    public void apagarDispositivo() {
        aire.apagar();
    }

    public void cambiarModoAAhorro() {
        aire.cambiarAmodoAhorro();
    }

    public void cambiarModoAPrendido() {
        aire.encender();
    }

    public void cambiarModoAApagado() {
        aire.apagar();
    }

    public void subirTemperatura(int cantidad) {
        aire.setTemperatura(aire.getTemperatura() + cantidad);
    }

    public void bajarTemperatura(int cantidad) {
        aire.setTemperatura(aire.getTemperatura() - cantidad);
    }

}

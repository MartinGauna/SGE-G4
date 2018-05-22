package martes.noche;

public class ActuadorLuz implements Actuador {
    private DispositivoInteligenteLuz luz;

    public ActuadorLuz(DispositivoInteligenteLuz luz) {
        this.luz = luz;
    }

    public void prenderDispositivo() {
        luz.encender();
    }
    public void apagarDispositivo() {
        luz.apagar();
    }

    public void cambiarModoAAhorro() {
        luz.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        luz.encender();
    }
    public void cambiarModoAApagado() {
        luz.apagar();
    }

    public void subirLuminosidad(int cantidad) {
        luz.setLuminosidad(luz.getLuminosidad() + cantidad);
    }

    public void bajarLuminosidad(int cantidad) {
        luz.setLuminosidad(luz.getLuminosidad() - cantidad);
    }
}

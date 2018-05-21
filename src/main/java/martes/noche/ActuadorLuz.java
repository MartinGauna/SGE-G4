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

    public void subirIntensidad(int cantidad) {
        luz.setIntensidad(luz.getIntensidad() + cantidad);
    }

    public void bajarIntensidad(int cantidad) {
        luz.setIntensidad(luz.getIntensidad() - cantidad);
    }
}

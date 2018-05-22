package martes.noche;

public class DispositivoInteligenteLuz extends DispositivoInteligente {

    private int luminosidad;

    public DispositivoInteligenteLuz(String nombre, int consumoHora, String estado) {
        super(nombre, consumoHora, estado);
        ActuadorLuz actuador = new ActuadorLuz(this);
        this.setActuador(actuador);
        this.setLuminosidad(20);
    }

    public int getLuminosidad() {
        return luminosidad;
    }

    public void setLuminosidad(int intensidad) {
        this.luminosidad = intensidad;
    }
}
package martes.noche;

public class DispositivoInteligenteLuz extends DispositivoInteligente {

    private int intensidad;

    public DispositivoInteligenteLuz(String nombre, int consumoHora, String estado) {
        super(nombre, consumoHora, estado);
        ActuadorLuz actuador = new ActuadorLuz(this);
        this.setActuador(actuador);
    }

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }
}
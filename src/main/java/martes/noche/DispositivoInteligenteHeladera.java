package martes.noche;

public class DispositivoInteligenteHeladera extends DispositivoInteligente {

    private int intensidad;

    public DispositivoInteligenteHeladera(String nombre, int consumoHora, String estado) {
        super(nombre, consumoHora, estado);
        ActuadorHeladera actuador = new ActuadorHeladera(this);
        this.setActuador(actuador);
    }

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }
}
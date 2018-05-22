package martes.noche;

public class DispositivoInteligenteAAcondicionado extends DispositivoInteligente {
    private int temperatura;

    public DispositivoInteligenteAAcondicionado(String nombre, int consumoHora, String estado) {
        super(nombre, consumoHora, estado);
        ActuadorAAcondicionado actuador = new ActuadorAAcondicionado(this);
        this.setActuador(actuador);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
}

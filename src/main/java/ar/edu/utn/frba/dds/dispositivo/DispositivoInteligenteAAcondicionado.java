package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorAAcondicionado;

public class DispositivoInteligenteAAcondicionado extends DispositivoInteligente {
    private int temperatura;

    public DispositivoInteligenteAAcondicionado(String nombre, int consumoHora, String estado) {
        super(nombre, consumoHora, estado,90,360);
        ActuadorAAcondicionado actuador = new ActuadorAAcondicionado(this);
        this.setActuador(actuador);
        this.setTemperatura(24);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
}

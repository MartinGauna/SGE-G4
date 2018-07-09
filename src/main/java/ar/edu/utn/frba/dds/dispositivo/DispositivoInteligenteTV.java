package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorTV;

public class DispositivoInteligenteTV extends DispositivoInteligente {

    private int canal;
    private int volumen;

    public DispositivoInteligenteTV(String nombre, int consumoHora, String estado) {
        super(nombre, consumoHora, estado,90,360);
        ActuadorTV actuador = new ActuadorTV(this);
        this.setActuador(actuador);
        this.setCanal(12);
        this.setVolumen(25);
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }
}

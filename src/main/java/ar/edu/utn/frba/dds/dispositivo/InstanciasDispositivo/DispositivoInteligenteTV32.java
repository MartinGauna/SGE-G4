package ar.edu.utn.frba.dds.dispositivo.InstanciasDispositivo;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteTV;

public class DispositivoInteligenteTV32 extends DispositivoInteligenteTV {
    private int temperatura;
    private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteTV32(String nombre, String estado) {
        super(nombre, 0.055, estado,true);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

}

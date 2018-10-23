package ar.edu.utn.frba.dds.dispositivo.InstanciasDispositivo;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteTV;

public class DispositivoInteligenteTV24 extends DispositivoInteligenteTV {
    private int temperatura;
    private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteTV24(String nombre, String estado) {
        super(nombre, 0.04, estado,true);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }


}

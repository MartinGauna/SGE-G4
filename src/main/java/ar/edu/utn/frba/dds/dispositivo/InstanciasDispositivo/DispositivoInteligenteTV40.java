package ar.edu.utn.frba.dds.dispositivo.InstanciasDispositivo;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteTV;

public class DispositivoInteligenteTV40 extends DispositivoInteligenteTV {
    private int temperatura;
    private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteTV40(String nombre, String estado) {
        super(nombre, 0.08, estado,true);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

}

package ar.edu.utn.frba.dds.dispositivo.InstanciasDispositivo;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteHeladera;

public class DispositivoInteligenteHeladeraConFreezer extends DispositivoInteligenteHeladera {
    private int temperatura;
        private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteHeladeraConFreezer(String nombre, String estado) {
        super(nombre, 0.09, estado,true);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

}

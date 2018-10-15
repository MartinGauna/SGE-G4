package ar.edu.utn.frba.dds.dispositivo.InstanciasDispositivo;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteVentilador;

public class DispositivoInteligenteVentiladorTecho extends DispositivoInteligenteVentilador {
    private int temperatura;
    private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteVentiladorTecho(String nombre, String estado) {
        super(nombre, 0.06, estado,true);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

}

package ar.edu.utn.frba.dds.dispositivo.InstanciasDispositivo;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteLavarropas;

public class DispositivoInteligenteLavarropas5 extends DispositivoInteligenteLavarropas {
    private int temperatura;
    private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteLavarropas5(String nombre, String estado) {
        super(nombre, 0.175, estado,true);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }


}

package ar.edu.utn.frba.dds.dispositivo.InstanciasDispositivo;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;

public class DispositivoInteligenteAAcondicionado3500 extends DispositivoInteligenteAAcondicionado {
    private int temperatura;
        private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteAAcondicionado3500(String nombre,  String estado) {
        super(nombre, 1.613, estado,false);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public int getUso_minimo() {
        return 90;
    }
    public int getUso_maximo() {
        return 360;
    }
}

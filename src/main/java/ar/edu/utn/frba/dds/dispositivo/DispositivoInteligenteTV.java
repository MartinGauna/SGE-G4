package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorTV;

public class DispositivoInteligenteTV extends DispositivoInteligente {

    private int canal;
    private int volumen;
    public int uso_maximo;
    public int uso_minimo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteTV(String nombre, double consumoHora, String estado, boolean esBajoConsumo) {
        super(nombre, consumoHora, estado,360,90);
        this.setUsoMaximo(360);
        this.setUsoMinimo(90);
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
    public void setUsoMaximo(int usoMaximo) {
        this.uso_maximo = usoMaximo;
    }
    public void setUsoMinimo(int usoMinimo) {
        this.uso_minimo = usoMinimo;
    }
}

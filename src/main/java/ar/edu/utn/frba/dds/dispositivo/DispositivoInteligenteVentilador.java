package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorVentilador;

public class DispositivoInteligenteVentilador extends DispositivoInteligente {

    private int canal;
    private int volumen;
    public int uso_maximo;
    public int uso_minimo;
    boolean esBajoConsumo;
    public DispositivoInteligenteVentilador(String nombre, double consumoHora, String estado,boolean esBajoConsumo) {
        super(nombre, consumoHora, estado,360,120);
        this.uso_maximo = 360;
        this.uso_minimo = 120;
        ActuadorVentilador actuador = new ActuadorVentilador(this);
        this.setActuador(actuador);

    }

}

package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorVentilador;

public class DispositivoInteligenteVentilador extends DispositivoInteligente {

    private int canal;
    private int volumen;

    public DispositivoInteligenteVentilador(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado);
        this.uso_maximo = 360;
        this.uso_minimo = 120;
        ActuadorVentilador actuador = new ActuadorVentilador(this);
        this.setActuador(actuador);

    }

}

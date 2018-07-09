package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorVentilador;

public class DispositivoInteligenteVentilador extends DispositivoInteligente {

    private int canal;
    private int volumen;

    public DispositivoInteligenteVentilador(String nombre, int consumoHora, String estado) {
        super(nombre, consumoHora, estado,120,360);
        ActuadorVentilador actuador = new ActuadorVentilador(this);
        this.setActuador(actuador);

    }

}

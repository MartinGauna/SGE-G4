package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorPC;

public class DispositivoInteligentePC extends DispositivoInteligente {


    public DispositivoInteligentePC(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado,60,360);
        ActuadorPC actuador = new ActuadorPC(this);
        this.setActuador(actuador);

    }

}

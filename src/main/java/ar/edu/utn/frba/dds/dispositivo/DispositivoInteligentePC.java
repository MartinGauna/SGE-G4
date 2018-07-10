package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorPC;

public class DispositivoInteligentePC extends DispositivoInteligente {


    public DispositivoInteligentePC(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado);
        this.uso_minimo = 360;
        this.uso_maximo = 60;
        ActuadorPC actuador = new ActuadorPC(this);
        this.setActuador(actuador);

    }

}

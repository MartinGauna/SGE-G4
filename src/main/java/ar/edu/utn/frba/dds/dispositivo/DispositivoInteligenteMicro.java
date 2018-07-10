package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorMicro;

public class DispositivoInteligenteMicro extends DispositivoInteligente {


    public DispositivoInteligenteMicro(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado,3,15);
        ActuadorMicro actuador = new ActuadorMicro(this);
        this.setActuador(actuador);

    }

}

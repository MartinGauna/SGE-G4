package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorMicro;

public class DispositivoInteligenteMicro extends DispositivoInteligente {
    private int uso_maximo;
    private int uso_minimo;

    public DispositivoInteligenteMicro(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado,15,3);
        this.uso_minimo = 3;
        this. uso_maximo = 15;
        ActuadorMicro actuador = new ActuadorMicro(this);
        this.setActuador(actuador);

    }

}

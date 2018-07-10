package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorLavarropas;

public class DispositivoInteligenteLavarropas extends DispositivoInteligente {
    public int uso_maximo;
    public int uso_minimo;

    public DispositivoInteligenteLavarropas(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado,30,6);
        this.uso_minimo = 6;
        this.uso_maximo = 30;
        ActuadorLavarropas actuador = new ActuadorLavarropas(this);
        this.setActuador(actuador);

    }

}

package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorLavarropas;

public class DispositivoInteligenteLavarropas extends DispositivoInteligente {


    public DispositivoInteligenteLavarropas(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado);
        this.uso_minimo = 6;
        this.uso_maximo = 30;
        ActuadorLavarropas actuador = new ActuadorLavarropas(this);
        this.setActuador(actuador);

    }

}

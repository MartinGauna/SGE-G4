package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorLavarropas;

public class DispositivoInteligenteLavarropas extends DispositivoInteligente {


    public DispositivoInteligenteLavarropas(String nombre, int consumoHora, String estado) {
        super(nombre, consumoHora, estado,6,30);
        ActuadorLavarropas actuador = new ActuadorLavarropas(this);
        this.setActuador(actuador);

    }

}

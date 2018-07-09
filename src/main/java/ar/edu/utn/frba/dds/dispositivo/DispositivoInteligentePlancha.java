package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorPlancha;

public class DispositivoInteligentePlancha extends DispositivoInteligente {


    public DispositivoInteligentePlancha(String nombre, int consumoHora, String estado) {
        super(nombre, consumoHora, estado,3,30);
        ActuadorPlancha actuador = new ActuadorPlancha(this);
        this.setActuador(actuador);

    }

}

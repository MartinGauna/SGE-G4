package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorPlancha;

public class DispositivoInteligentePlancha extends DispositivoInteligente {


    public DispositivoInteligentePlancha(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado);
        this.uso_minimo = 3;
        this.uso_maximo = 30;
        ActuadorPlancha actuador = new ActuadorPlancha(this);
        this.setActuador(actuador);

    }

}

package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorPlancha;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class DispositivoInteligentePlancha extends DispositivoInteligente {
    @NotNull
    private int uso_maximo;
    @NotNull
    private int uso_minimo;

    protected DispositivoInteligentePlancha() {

    }

    public DispositivoInteligentePlancha(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado,30,3);
        this.uso_minimo = 3;
        this.uso_maximo = 30;
        ActuadorPlancha actuador = new ActuadorPlancha(this);
        this.setActuador(actuador);

    }

}

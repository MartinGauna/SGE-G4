package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligentePlancha;

import javax.persistence.*;

@Table
@Entity
public class ActuadorPlancha extends Actuador {

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligentePlancha.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_PLANCHA"))
    private DispositivoInteligentePlancha plancha;

    public ActuadorPlancha(DispositivoInteligentePlancha plancha) {
        this.plancha = plancha;
    }

    public ActuadorPlancha() {
    }

    public DispositivoInteligentePlancha getDispositivo() {
        return plancha;
    }

    public void prenderDispositivo(DispositivoInteligente d) {d.encender();
    }
    public void apagarDispositivo(DispositivoInteligente d) {
        d.apagar();
    }

    public void cambiarModoAAhorro(DispositivoInteligente d) {
        d.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        plancha.encender();
    }
    public void cambiarModoAApagado() {
        plancha.apagar();
    }

}

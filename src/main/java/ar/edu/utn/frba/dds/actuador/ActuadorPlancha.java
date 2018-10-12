package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligentePlancha;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorColumn(name="AACONDICIONADO")
public class ActuadorPlancha extends Actuador {

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligentePlancha.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_PLANCHA"))
    private DispositivoInteligentePlancha plancha;

    public ActuadorPlancha(DispositivoInteligentePlancha plancha) {
        this.plancha = plancha;
    }

    public ActuadorPlancha() {
    }

    public void prenderDispositivo() {plancha.encender();
    }
    public void apagarDispositivo() {
        plancha.apagar();
    }

    public void cambiarModoAAhorro() {
        plancha.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        plancha.encender();
    }
    public void cambiarModoAApagado() {
        plancha.apagar();
    }

}

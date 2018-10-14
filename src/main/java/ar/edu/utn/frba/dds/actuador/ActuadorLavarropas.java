package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteLavarropas;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class ActuadorLavarropas extends Actuador {

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligenteLavarropas.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id",foreignKey = @ForeignKey(name = "FK_ACTUADOR_LAVARROPA"))
    private DispositivoInteligenteLavarropas lr;

    public ActuadorLavarropas(DispositivoInteligenteLavarropas lr) {
        this.lr = lr;
    }

    public ActuadorLavarropas() {
    }

    public void prenderDispositivo() {lr.encender();
    }
    public void apagarDispositivo() {
        lr.apagar();
    }

    public void cambiarModoAAhorro() {
        lr.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        lr.encender();
    }
    public void cambiarModoAApagado() {
        lr.apagar();
    }

}

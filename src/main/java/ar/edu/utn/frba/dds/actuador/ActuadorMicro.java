package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteMicro;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class ActuadorMicro extends Actuador {

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligenteMicro.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_MICRO"))
    private DispositivoInteligenteMicro micro;

    public ActuadorMicro(DispositivoInteligenteMicro micro) {
        this.micro = micro;
    }

    public ActuadorMicro() {
    }

    public DispositivoInteligenteMicro getDispositivo() {
        return micro;
    }

    public void prenderDispositivo() {micro.encender();
    }
    public void apagarDispositivo() {
        micro.apagar();
    }

    public void cambiarModoAAhorro() {
        micro.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        micro.encender();
    }
    public void cambiarModoAApagado() {
        micro.apagar();
    }

}


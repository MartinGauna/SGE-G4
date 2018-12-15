package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteMicro;

import javax.persistence.*;

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

    public void prenderDispositivo(DispositivoInteligente d) {d.encender();
    }
    public void apagarDispositivo(DispositivoInteligente d) {
        d.apagar();
    }

    public void cambiarModoAAhorro(DispositivoInteligente d) {
        d.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        micro.encender();
    }
    public void cambiarModoAApagado() {
        micro.apagar();
    }

}


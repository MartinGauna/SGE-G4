package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligentePC;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorColumn(name="PC")
public class ActuadorPC extends Actuador {

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligentePC.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_PC"))

    private DispositivoInteligentePC pc;

    public ActuadorPC(DispositivoInteligentePC pc) {
        this.pc = pc;
    }

    public ActuadorPC() {
    }

    public void prenderDispositivo() {pc.encender();
    }
    public void apagarDispositivo() {
        pc.apagar();
    }

    public void cambiarModoAAhorro() {
        pc.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        pc.encender();
    }
    public void cambiarModoAApagado() {
        pc.apagar();
    }

}

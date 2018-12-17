package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligentePC;

import javax.persistence.*;

@Table
@Entity
public class ActuadorPC extends Actuador {

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligentePC.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_PC"))

    private DispositivoInteligentePC pc;

    public ActuadorPC(DispositivoInteligentePC pc) {
        this.pc = pc;
    }

    public ActuadorPC() {
    }

    public DispositivoInteligentePC getDispositivo() {
        return pc;
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
        pc.encender();
    }
    public void cambiarModoAApagado() {
        pc.apagar();
    }

}

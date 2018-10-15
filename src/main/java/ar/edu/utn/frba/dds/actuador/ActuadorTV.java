package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteTV;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class ActuadorTV extends Actuador {

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligenteAAcondicionado.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_TV"))
    private DispositivoInteligenteTV tv;

    public ActuadorTV(DispositivoInteligenteTV tv) {
        this.tv = tv;
    }

    public ActuadorTV() {
    }

    public void prenderDispositivo() {
        tv.encender();
    }
    public void apagarDispositivo() {
        tv.apagar();
    }

    public void cambiarModoAAhorro() {
        tv.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        tv.encender();
    }
    public void cambiarModoAApagado() {
        tv.apagar();
    }

    public void cambiarACanal(int canal) {
        tv.setCanal(canal);
    }

    public void subirCanal() {
        tv.setCanal(tv.getCanal() + 1);
    }

    public void bajarCanal() {
        tv.setCanal(tv.getCanal() - 1);
    }

    public void subirVolumen() {
        tv.setVolumen(tv.getVolumen() + 1);
    }

    public void bajarVolumen() {
        tv.setVolumen(tv.getVolumen() - 1);
    }
}

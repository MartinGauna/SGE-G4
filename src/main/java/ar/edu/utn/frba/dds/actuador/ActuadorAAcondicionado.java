package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorColumn(name="AACONDICIONADO")
public class ActuadorAAcondicionado extends Actuador {

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligenteAAcondicionado.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_AACOND"))
    private DispositivoInteligenteAAcondicionado aire;

    public ActuadorAAcondicionado(DispositivoInteligenteAAcondicionado aire) {
        this.aire = aire;
    }

    public ActuadorAAcondicionado() {
    }

    public void prenderDispositivo() {
        aire.encender();
    }

    public void apagarDispositivo() {
        aire.apagar();
    }

    public void cambiarModoAAhorro() {
        aire.cambiarAmodoAhorro();
    }

    public void cambiarModoAPrendido() {
        aire.encender();
    }

    public void cambiarModoAApagado() {
        aire.apagar();
    }

    public void subirTemperatura(int cantidad) {
        aire.setTemperatura(aire.getTemperatura() + cantidad);
    }

    public void bajarTemperatura(int cantidad) {
        aire.setTemperatura(aire.getTemperatura() - cantidad);
    }

}

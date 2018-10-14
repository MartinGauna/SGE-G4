package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteHeladera;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class ActuadorHeladera extends Actuador {

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligenteHeladera.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_HELADERA"))
    private DispositivoInteligenteHeladera heladera;

    public ActuadorHeladera(DispositivoInteligenteHeladera heladera) {
        this.heladera = heladera;
    }

    public ActuadorHeladera() {
    }

    public void prenderDispositivo() {
        heladera.encender();
    }
    public void apagarDispositivo() {
        heladera.apagar();
    }

    public void cambiarModoAAhorro() {
        heladera.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        heladera.encender();
    }
    public void cambiarModoAApagado() {
        heladera.apagar();
    }

    public void subirIntensidad(int cantidad) {
        heladera.setIntensidad(heladera.getIntensidad() + cantidad);
    }

    public void bajarIntensidad(int cantidad) {
        heladera.setIntensidad(heladera.getIntensidad() - cantidad);
    }
}

package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteHeladera;

import javax.persistence.*;

@Table
@Entity
public class ActuadorHeladera extends Actuador {

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligenteHeladera.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_HELADERA"))
    private DispositivoInteligenteHeladera heladera;

    public ActuadorHeladera(DispositivoInteligenteHeladera heladera) {
        this.heladera = heladera;
        heladera.setActuador(this);
    }

    public ActuadorHeladera() {
    }

    public DispositivoInteligenteHeladera getDispositivo() {
        return heladera;
    }


    public void prenderDispositivo(DispositivoInteligente d) {
        d.encender();
    }
    public void apagarDispositivo(DispositivoInteligente d) {
        d.apagar();
    }

    public void cambiarModoAAhorro(DispositivoInteligente d) {
        d.cambiarAmodoAhorro();
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

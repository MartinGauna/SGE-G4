package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteVentilador;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorColumn(name="VENTILADOR")
public class ActuadorVentilador extends Actuador {

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligenteVentilador.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_VENT"))
    private DispositivoInteligenteVentilador ventilador;

    public ActuadorVentilador(DispositivoInteligenteVentilador ventilador) {
        this.ventilador = ventilador;
    }

    public ActuadorVentilador() {
    }

    public void prenderDispositivo() {ventilador.encender();
    }
    public void apagarDispositivo() {
        ventilador.apagar();
    }

    public void cambiarModoAAhorro() {
        ventilador.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        ventilador.encender();
    }
    public void cambiarModoAApagado() {
        ventilador.apagar();
    }

}

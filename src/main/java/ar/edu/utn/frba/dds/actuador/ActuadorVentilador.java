package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteVentilador;

import javax.persistence.*;

@Table
@Entity
public class ActuadorVentilador extends Actuador {

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligenteVentilador.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_VENT"))
    private DispositivoInteligenteVentilador ventilador;

    public ActuadorVentilador(DispositivoInteligenteVentilador ventilador) {
        this.ventilador = ventilador;
    }

    public ActuadorVentilador() {
    }

    public DispositivoInteligenteVentilador getDispositivo() {
        return ventilador;
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
        ventilador.encender();
    }
    public void cambiarModoAApagado() {
        ventilador.apagar();
    }

}

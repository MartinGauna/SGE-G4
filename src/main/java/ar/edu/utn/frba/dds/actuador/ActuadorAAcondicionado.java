package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;

import javax.persistence.*;

@Table
@Entity
public class ActuadorAAcondicionado extends Actuador {

    public void setAire(DispositivoInteligenteAAcondicionado aire) {
        this.aire = aire;
    }

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligenteAAcondicionado.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_AACOND"))
    private DispositivoInteligenteAAcondicionado aire;

    public ActuadorAAcondicionado(DispositivoInteligenteAAcondicionado aire) {
        this.aire = aire;
        aire.setActuador(this);
    }

    public ActuadorAAcondicionado() {
    }

    public DispositivoInteligenteAAcondicionado getDispositivo() {
        return aire;
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

package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteTV;

import javax.persistence.*;

@Table
@Entity
public class ActuadorTV extends Actuador {

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligenteTV.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_TV"))
    private DispositivoInteligenteTV tv;

    public ActuadorTV(DispositivoInteligenteTV tv) {
        this.tv = tv;
        tv.setActuador(this);
    }

    public ActuadorTV() {
    }

    public DispositivoInteligenteTV getDispositivo() {
        return tv;
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

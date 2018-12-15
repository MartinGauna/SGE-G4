package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteLavarropas;

import javax.persistence.*;

@Table
@Entity
public class ActuadorLavarropas extends Actuador {

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligenteLavarropas.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id",foreignKey = @ForeignKey(name = "FK_ACTUADOR_LAVARROPA"))
    private DispositivoInteligenteLavarropas lr;

    public ActuadorLavarropas(DispositivoInteligenteLavarropas lr) {
        this.lr = lr;
    }

    public ActuadorLavarropas() {
    }

    public DispositivoInteligenteLavarropas getDispositivo() {
        return lr;
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
        lr.encender();
    }
    public void cambiarModoAApagado() {
        lr.apagar();
    }

}

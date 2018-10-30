package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteLavarropas;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteLuz;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class ActuadorLuz extends Actuador {

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligenteLuz.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR_LUZ"))
    private DispositivoInteligenteLuz luz;

    public ActuadorLuz(DispositivoInteligenteLuz luz) {
        this.luz = luz;
    }

    public ActuadorLuz() {
    }

    public DispositivoInteligenteLuz getDispositivo() {
        return luz;
    }

    public void prenderDispositivo() {
        luz.encender();
    }
    public void apagarDispositivo() {
        luz.apagar();
    }

    public void cambiarModoAAhorro() {
        luz.cambiarAmodoAhorro();
    }
    public void cambiarModoAPrendido() {
        luz.encender();
    }
    public void cambiarModoAApagado() {
        luz.apagar();
    }

    public void subirLuminosidad(int cantidad) {
        luz.setLuminosidad(luz.getLuminosidad() + cantidad);
    }

    public void bajarLuminosidad(int cantidad) {
        luz.setLuminosidad(luz.getLuminosidad() - cantidad);
    }
}

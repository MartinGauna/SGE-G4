package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteLuz;

import javax.persistence.*;

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

package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Table
public abstract class Actuador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Transient
    private DispositivoInteligente disp = null;
    public abstract void prenderDispositivo(DispositivoInteligente d);
    public abstract void apagarDispositivo(DispositivoInteligente d);

    public abstract void cambiarModoAAhorro(DispositivoInteligente d);
    public abstract void cambiarModoAPrendido();
    public abstract void cambiarModoAApagado();

    public int getId() {
        return id;
    }

}

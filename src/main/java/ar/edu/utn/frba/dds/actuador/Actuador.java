package ar.edu.utn.frba.dds.actuador;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoActuador")
@Table
public abstract class Actuador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public abstract void prenderDispositivo();
    public abstract void apagarDispositivo();

    public abstract void cambiarModoAAhorro();
    public abstract void cambiarModoAPrendido();
    public abstract void cambiarModoAApagado();

    public int getId() {
        return id;
    }

}

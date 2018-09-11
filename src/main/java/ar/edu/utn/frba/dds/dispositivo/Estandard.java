package ar.edu.utn.frba.dds.dispositivo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class Estandard extends Dispositivo {

    //Class Properties
    //Note: Dispositivo properties (inherating): String nombre, int consumoHora,  String estado;
    @NotNull
    Adaptador adaptador;

    public Estandard(String nombre, int consumoEstimado, String estado) {
        super(nombre, consumoEstimado, estado);
    }

    public void convertir(Adaptador adaptador) {
        this.adaptador = adaptador;
    }

    public Adaptador getAdaptador() {
        return adaptador;
    }

    public void setAdaptador(Adaptador adaptador) {
        this.adaptador = adaptador;
    }

}

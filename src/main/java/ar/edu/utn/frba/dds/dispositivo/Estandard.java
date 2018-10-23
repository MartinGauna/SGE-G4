package ar.edu.utn.frba.dds.dispositivo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table
@Entity
public class Estandard extends Dispositivo {

    //Class Properties
    //Note: Dispositivo properties (inherating): String nombre, int consumoHora,  String estado;
    @Transient
    Adaptador adaptador = null;


    public Estandard(String nombre, int consumoEstimado, String estado) {
        super(nombre, consumoEstimado, estado);
    }

    public Estandard(){

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

    public int getConsumoTotal(Date fechaInicio, Date fechaFinal) {
        if(adaptador != null) {
            return adaptador.getConsumoTotal(fechaInicio, fechaFinal);
        }
        return 0;
    }

}

package ar.edu.utn.frba.dds;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Magnitud {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private long valor;
    @NotNull
    private String magnitud;

    /**
     * Getters & Setters
     */

    public int getId() { return id; }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }
    /*====================================================*/

    public Magnitud (){
        this.valor =  0;
        this.magnitud = "";
    }

    public Magnitud (long valor, String magnitud){
        this.valor =  valor;
        this.magnitud = magnitud;
    }
}

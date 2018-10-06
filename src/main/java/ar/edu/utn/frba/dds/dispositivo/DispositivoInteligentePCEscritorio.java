package ar.edu.utn.frba.dds.dispositivo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//@Table
//@Entity
public class DispositivoInteligentePCEscritorio extends DispositivoInteligentePC {
    //@NotNull
    private int temperatura;
    //@NotNull
    private double consumo;
    //@NotNull
    private  boolean esBajoConsumo;

    protected DispositivoInteligentePCEscritorio() {

    }

    public DispositivoInteligentePCEscritorio(String nombre, String estado) {
        super(nombre, 0.4, estado,true);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

}

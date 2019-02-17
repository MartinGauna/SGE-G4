package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorPC;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class DispositivoInteligentePC extends DispositivoInteligente {
    @NotNull
    private int uso_maximo;
    @NotNull
    private int uso_minimo;
    @NotNull
    boolean esBajoConsumo;

    protected DispositivoInteligentePC(){

    }

    public DispositivoInteligentePC(String nombre, double consumoHora, String estado,boolean esBajoConsumo) {
        super(nombre, consumoHora, estado,360,90);
        this.uso_minimo = 360;
        this.uso_maximo = 60;
    }

}

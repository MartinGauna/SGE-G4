package ar.edu.utn.frba.dds.dispositivo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class DispositivoInteligentePC extends DispositivoInteligente {
    @NotNull
    boolean esBajoConsumo;

    protected DispositivoInteligentePC(){

    }

    public DispositivoInteligentePC(String nombre, double consumoHora, String estado,boolean esBajoConsumo) {
        super(nombre, consumoHora, estado,360,90);
        this.esBajoConsumo = esBajoConsumo;
    }

}

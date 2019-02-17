package ar.edu.utn.frba.dds.dispositivo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class DispositivoInteligenteLavarropas extends DispositivoInteligente {
    @NotNull
    boolean esBajoConsumo;

    protected DispositivoInteligenteLavarropas() {

    }

    public DispositivoInteligenteLavarropas(String nombre, double consumoHora, String estado,boolean esBajoConsumo) {
        super(nombre, consumoHora, estado, 30, 6);
        this.esBajoConsumo = esBajoConsumo;
    }

}

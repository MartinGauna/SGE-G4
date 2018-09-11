package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorLavarropas;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class DispositivoInteligenteLavarropas extends DispositivoInteligente {
    @NotNull
    public int uso_maximo;
    @NotNull
    public int uso_minimo;
    @NotNull
    boolean esBajoConsumo;

    protected DispositivoInteligenteLavarropas() {

    }

    public DispositivoInteligenteLavarropas(String nombre, double consumoHora, String estado,boolean esBajoConsumo) {
        super(nombre, consumoHora, estado,30,6);
        this.uso_minimo = 6;
        this.uso_maximo = 30;
        ActuadorLavarropas actuador = new ActuadorLavarropas(this);
        this.setActuador(actuador);

    }

}

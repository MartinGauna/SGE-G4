package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorVentilador;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class DispositivoInteligenteVentilador extends DispositivoInteligente {

    @NotNull
    public int uso_maximo;
    @NotNull
    public int uso_minimo;
    @NotNull
    boolean esBajoConsumo;

    protected DispositivoInteligenteVentilador() {

    }

    public DispositivoInteligenteVentilador(String nombre, double consumoHora, String estado,boolean esBajoConsumo) {
        super(nombre, consumoHora, estado,360,120);
        this.uso_maximo = 360;
        this.uso_minimo = 120;
        ActuadorVentilador actuador = new ActuadorVentilador(this);
        this.setActuador(actuador);

    }

}

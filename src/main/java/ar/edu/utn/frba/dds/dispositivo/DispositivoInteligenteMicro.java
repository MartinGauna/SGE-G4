package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorMicro;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class DispositivoInteligenteMicro extends DispositivoInteligente {
    @NotNull
    private int uso_maximo;
    @NotNull
    private int uso_minimo;

    protected DispositivoInteligenteMicro() {

    }

    public DispositivoInteligenteMicro(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado,15,3);
        this.uso_minimo = 3;
        this. uso_maximo = 15;
        ActuadorMicro actuador = new ActuadorMicro(this);
        this.setActuador(actuador);

    }

}

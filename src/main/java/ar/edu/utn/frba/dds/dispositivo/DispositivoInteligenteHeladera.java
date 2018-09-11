package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorHeladera;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class DispositivoInteligenteHeladera extends DispositivoInteligente {
    @NotNull
    private int intensidad;
    @NotNull
    private int uso_maximo;
    @NotNull
    private int uso_minimo;
    @NotNull
    private  boolean esBajoConsumo;

    protected DispositivoInteligenteHeladera(){
        super();
    }

    public DispositivoInteligenteHeladera(String nombre, double consumoHora, String estado, boolean esBajoConsumo) {
        super(nombre, consumoHora, estado,360,90);
        this.uso_minimo = 90;
        this. uso_maximo = 360;
        ActuadorHeladera actuador = new ActuadorHeladera(this);
        this.setActuador(actuador);
        this.setIntensidad(0);
    }

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }
}
package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorAAcondicionado;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Table
@Entity
public class DispositivoInteligenteAAcondicionado extends DispositivoInteligente {
    @NotNull
    private int temperatura;
    @NotNull
    public int uso_maximo;
    @NotNull
    public int uso_minimo;
    @NotNull
    private double consumo;
    @NotNull
    private  boolean esBajoConsumo;

    protected  DispositivoInteligenteAAcondicionado() {

    }

    public DispositivoInteligenteAAcondicionado(String nombre, double consumoHora, String estado, boolean esBajoConsumo)
    {
        super(nombre, consumoHora, estado,360,90);
        this.uso_minimo = 90;
        this.uso_maximo = 360;
        ActuadorAAcondicionado actuador = new ActuadorAAcondicionado(this);
        this.setActuador(actuador);
        this.setTemperatura(24);
        this.setEsBajoConsumo(esBajoConsumo);
        this.setConsumo(consumo);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public int getUso_minimo() {
        return uso_minimo;
    }
    public int getUso_maximo() {
        return uso_maximo;
    }

    public boolean isEsBajoConsumo() {
        return esBajoConsumo;
    }

    public void setEsBajoConsumo(boolean esBajoConsumo) {
        this.esBajoConsumo = esBajoConsumo;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }
}


package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorLuz;
import ar.edu.utn.frba.dds.sensor.SensorHumedad;
import ar.edu.utn.frba.dds.sensor.SensorLuz;
import ar.edu.utn.frba.dds.sensor.SensorTemperatura;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class DispositivoInteligenteLuz extends DispositivoInteligente {

    @NotNull
    private int luminosidad;
    @NotNull
    private int uso_maximo;
    @NotNull
    private int uso_minimo;
    @NotNull
    boolean esBajoConsumo;

    protected DispositivoInteligenteLuz() {

    }

    public DispositivoInteligenteLuz(String nombre, double consumoHora, String estado,boolean esBajoConsumo) {
        super(nombre, consumoHora, estado,360,90);
        this.uso_maximo = 360;
        this.uso_minimo = 90;
        this.setLuminosidad(20);

        this.sensores.add(new SensorLuz(1, this));
    }

    public int getLuminosidad() {
        return luminosidad;
    }

    public void setLuminosidad(int intensidad) {
        this.luminosidad = intensidad;
    }
}
package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.sensor.SensorLuz;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class DispositivoInteligenteLuz extends DispositivoInteligente {

    @NotNull
    private int luminosidad;
    @NotNull
    boolean esBajoConsumo;

    protected DispositivoInteligenteLuz() {

    }

    public DispositivoInteligenteLuz(String nombre, double consumoHora, String estado,boolean esBajoConsumo) {
        super(nombre, consumoHora, estado,360,90);
        this.setLuminosidad(20);
        this.esBajoConsumo = esBajoConsumo;
        this.sensores.add(new SensorLuz(1, this));
    }

    public int getLuminosidad() {
        return luminosidad;
    }

    public void setLuminosidad(int intensidad) {
        this.luminosidad = intensidad;
    }

    public void bajar(int cantidad) {
        luminosidad = luminosidad - cantidad;
        if(luminosidad < 0){
            luminosidad = 0;
        }
    }

    public void subir(int cantidad) {
        luminosidad = luminosidad + cantidad;
    }
}
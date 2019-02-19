package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.sensor.SensorHumedad;
import ar.edu.utn.frba.dds.sensor.SensorTemperatura;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class DispositivoInteligenteHeladera extends DispositivoInteligente {
    @NotNull
    private int intensidad;
    @NotNull
    private  boolean esBajoConsumo;

    protected DispositivoInteligenteHeladera(){
        super();
    }

    public DispositivoInteligenteHeladera(String nombre, double consumoHora, String estado, boolean esBajoConsumo) {
        super(nombre, consumoHora, estado,360,90);
        this.setIntensidad(0);
        this.esBajoConsumo = esBajoConsumo;
        this.sensores.add(new SensorTemperatura(1, this));
        this.sensores.add(new SensorHumedad(1, this));
    }

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }

    public void bajar(int cantidad) {
        intensidad = intensidad - cantidad;
        if(intensidad < 0){
            intensidad = 0;
        }
    }

    public void subir(int cantidad) {
        intensidad = intensidad + cantidad;
    }
}
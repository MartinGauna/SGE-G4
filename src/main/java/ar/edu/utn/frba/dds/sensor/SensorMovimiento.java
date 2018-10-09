package ar.edu.utn.frba.dds.sensor;

import ar.edu.utn.frba.dds.Magnitud;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


public class SensorMovimiento extends Sensor {

    public SensorMovimiento() {
        this.setMagnitud(new Magnitud(0, "m"));
        this.setIntervalo(0);
    }

    public SensorMovimiento(long intervalo) {
        super(intervalo, "m", 30);
    }

    @Override
    public Magnitud getMedicion() {
        return this.getMagnitud();
    }
}

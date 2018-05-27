package ar.edu.utn.frba.dds.sensor;

import ar.edu.utn.frba.dds.Magnitud;

public class SensorHumedad extends Sensor{

    public SensorHumedad() {
        this.setMagnitud(new Magnitud(0, "RH"));
        this.setIntervalo(0);
    }

    public SensorHumedad(long intervalo) {
        super(intervalo, "RH", 300);
        this.setIntervalo(intervalo);
    }

    @Override
    public Magnitud getMedicion() {
        return this.getMagnitud();
    }
}
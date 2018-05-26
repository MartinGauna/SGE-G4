package ar.edu.utn.frba.dds.sensor;

import ar.edu.utn.frba.dds.Magnitud;

public class SensorLuz extends Sensor{

    public SensorLuz() {
        this.setMagnitud(new Magnitud(0, "Hz"));
        this.setIntervalo(0);
    }

    public SensorLuz(long intervalo) {
        super(intervalo, "Hz",300);
    }

    @Override
    public Magnitud getMedicion() {
        return this.getMagnitud();
    }
}

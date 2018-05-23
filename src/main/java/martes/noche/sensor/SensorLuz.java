package martes.noche.sensor;

import martes.noche.Magnitud;

import java.util.Random;

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

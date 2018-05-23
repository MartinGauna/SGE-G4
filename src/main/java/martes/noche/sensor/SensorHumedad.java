package martes.noche.sensor;

import martes.noche.Magnitud;

import java.util.Random;

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

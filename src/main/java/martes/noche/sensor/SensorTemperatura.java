package martes.noche.sensor;

import martes.noche.Magnitud;

import java.util.Random;

public class SensorTemperatura extends Sensor {

    public SensorTemperatura() {
        this.setMagnitud(new Magnitud(0, "°C"));
        this.setIntervalo(0);
    }

    public SensorTemperatura(long intervalo) {
        super(0, "°C", 40);
    }

    @Override
    public Magnitud getMedicion() {
        return this.getMagnitud();
    }
}

package martes.noche.sensor;

import martes.noche.Magnitud;

import java.util.Random;

public class SensorMovimiento extends Sensor {

    public SensorMovimiento() {
        this.setMagnitud(new Magnitud(0, "m"));
        this.setIntervalo(0);
    }

    public SensorMovimiento(long intervalo) {
        this.setMagnitud(new Magnitud(0, "m"));
        this.setIntervalo(intervalo);
    }

    @Override
    public Magnitud getMedicion() {

        Random rand = new Random(30);
        this.magnitud.setValor(rand.nextLong());
        return this.magnitud;
    }
}

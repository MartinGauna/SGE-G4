package martes.noche.sensor;

import martes.noche.Magnitud;

public class SensorMovimiento extends Sensor {

    public SensorMovimiento(){
        this.setMagnitud(new Magnitud(0, "m"));
        this.setIntervalo(0);
    }

    public SensorMovimiento(long intervalo){
        this.setMagnitud(new Magnitud(0, "m"));
        this.setIntervalo(intervalo);
    }

    @Override
    public Magnitud getMedicion() {
        return this.getMagnitud();
    }
}

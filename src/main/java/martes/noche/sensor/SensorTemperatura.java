package martes.noche.sensor;

import martes.noche.Magnitud;

public class SensorTemperatura extends Sensor {

    public SensorTemperatura(){
        this.setMagnitud(new Magnitud(0, "°C"));
        this.setIntervalo(0);
    }

    public SensorTemperatura(long intervalo){
        this.setMagnitud(new Magnitud(0, "°C"));
        this.setIntervalo(intervalo);
    }

    @Override
    public Magnitud getMedicion() {
        return this.getMagnitud();
    }
}

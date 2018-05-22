package martes.noche.sensor;

import martes.noche.Magnitud;

public class SensorHumedad extends Sensor{

    public SensorHumedad(){
        this.setMagnitud(new Magnitud(0, "RH"));
        this.setIntervalo(0);
    }

    public SensorHumedad(long intervalo){
        this.setMagnitud(new Magnitud(0, "RH"));
        this.setIntervalo(intervalo);
    }

    @Override
    public Magnitud getMedicion() {
        return this.getMagnitud();
    }
}

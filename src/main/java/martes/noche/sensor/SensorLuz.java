package martes.noche.sensor;

import martes.noche.Magnitud;

public class SensorLuz extends Sensor{

    public SensorLuz(){
        this.setMagnitud(new Magnitud(0, "Hz"));
        this.setIntervalo(0);
    }

    public SensorLuz(long intervalo){
        this.setMagnitud(new Magnitud(0, "Hz"));
        this.setIntervalo(intervalo);
    }

    @Override
    public Magnitud getMedicion() {
        return this.getMagnitud();
    }
}

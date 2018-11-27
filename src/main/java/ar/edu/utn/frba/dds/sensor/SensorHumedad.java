package ar.edu.utn.frba.dds.sensor;

import ar.edu.utn.frba.dds.Magnitud;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorColumn(name="H")
public class SensorHumedad extends Sensor{

    public SensorHumedad() {
        this.setMagnitud(new Magnitud(0, "RH"));
        this.setIntervalo(0);
    }

    public SensorHumedad(long intervalo) {
        super(intervalo, "RH", 100);
        this.setIntervalo(intervalo);
    }

    public SensorHumedad(long intervalo, DispositivoInteligente d) {
        super(intervalo, "RH", 100, d);
        this.setIntervalo(intervalo);
    }

    @Override
    public Magnitud getMedicion() {
        return this.getMagnitud();
    }
}

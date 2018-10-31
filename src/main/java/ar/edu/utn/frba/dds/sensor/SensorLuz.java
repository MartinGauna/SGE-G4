package ar.edu.utn.frba.dds.sensor;

import ar.edu.utn.frba.dds.Magnitud;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorColumn(name="L")
public class SensorLuz extends Sensor{

    public SensorLuz() {
        this.setMagnitud(new Magnitud(0, "Hz"));
        this.setIntervalo(0);
    }

    public SensorLuz(long intervalo) {
        super(intervalo, "Hz",300);
    }

    public SensorLuz(long intervalo, DispositivoInteligente d) {
        super(intervalo, "Hz",300, d);
    }

    @Override
    public Magnitud getMedicion() {
        return this.getMagnitud();
    }
}

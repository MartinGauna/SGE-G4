package ar.edu.utn.frba.dds.sensor;

import ar.edu.utn.frba.dds.Magnitud;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorColumn(name="M")
public class SensorMovimiento extends Sensor {

    public SensorMovimiento() {
        this.setMagnitud(new Magnitud(0, "m"));
        this.setIntervalo(0);
    }

    public SensorMovimiento(long intervalo) {
        super(intervalo, "m", 30);
    }

    public SensorMovimiento(long intervalo, DispositivoInteligente d) {
        super(intervalo, "m", 30, d);
    }

    @Override
    public Magnitud getMedicion() {
        return this.getMagnitud();
    }
}

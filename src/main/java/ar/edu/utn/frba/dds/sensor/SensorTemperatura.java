package ar.edu.utn.frba.dds.sensor;

import ar.edu.utn.frba.dds.Magnitud;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/*@Entity
@Table
@DiscriminatorValue("T")*/
public class SensorTemperatura extends Sensor {

    public SensorTemperatura() {
        this.setMagnitud(new Magnitud(0, "°C"));
        this.setIntervalo(0);
    }

    public SensorTemperatura(long intervalo) {

        //Agregado por Matias: paso el intervalo como parametro (antes estaba hardcodeado en intervalo: 0
        super(intervalo, "°C", 40);

        //Agregado por Matias para replicar comportamiento de SensorHumedad -->
        this.setIntervalo(intervalo);
    }

    @Override
    public Magnitud getMedicion() {
        return this.getMagnitud();
    }
}

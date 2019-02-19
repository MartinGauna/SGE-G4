package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.sensor.SensorHumedad;
import ar.edu.utn.frba.dds.sensor.SensorTemperatura;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Table
@Entity
public class DispositivoInteligenteAAcondicionado extends DispositivoInteligente {
    @NotNull
    private int temperatura;


    @NotNull
    private  boolean esBajoConsumo;

    protected  DispositivoInteligenteAAcondicionado() {

    }

    public DispositivoInteligenteAAcondicionado(String nombre, double consumoHora, String estado, boolean esBajoConsumo)
    {
        super(nombre, consumoHora, estado,360,90);
        this.setTemperatura(24);
        this.setEsBajoConsumo(esBajoConsumo);
        this.sensores.add(new SensorTemperatura(1, this));
        this.sensores.add(new SensorHumedad(1, this));
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public boolean isEsBajoConsumo() {
        return esBajoConsumo;
    }

    public void setEsBajoConsumo(boolean esBajoConsumo) {
        this.esBajoConsumo = esBajoConsumo;
    }

    public void bajar(int cantidad) {
        temperatura = temperatura - cantidad;
        if(temperatura < 0){
            temperatura = 0;
        }
    }

    public void subir(int cantidad) {
        temperatura = temperatura + cantidad;
    }

}


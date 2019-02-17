package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.sensor.SensorTemperatura;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class DispositivoInteligenteVentilador extends DispositivoInteligente {

    @NotNull
    boolean esBajoConsumo;

    protected DispositivoInteligenteVentilador() {

    }

    public DispositivoInteligenteVentilador(String nombre, double consumoHora, String estado,boolean esBajoConsumo) {
        super(nombre, consumoHora, estado,360,120);
        this.esBajoConsumo = esBajoConsumo;
        this.sensores.add(new SensorTemperatura(1, this));
    }

}
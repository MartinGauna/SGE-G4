package ar.edu.utn.frba.dds.dispositivo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
public class DispositivoInteligentePlancha extends DispositivoInteligente {

    protected DispositivoInteligentePlancha() {

    }

    public DispositivoInteligentePlancha(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado,30,3);
    }

}

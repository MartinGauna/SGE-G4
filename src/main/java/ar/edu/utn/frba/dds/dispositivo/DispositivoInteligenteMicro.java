package ar.edu.utn.frba.dds.dispositivo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
public class DispositivoInteligenteMicro extends DispositivoInteligente {

    protected DispositivoInteligenteMicro() {

    }

    public DispositivoInteligenteMicro(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado,15,3);
    }

}

package ar.edu.utn.frba.dds.dispositivo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class DispositivoInteligenteTV extends DispositivoInteligente {

    @NotNull
    private int canal;
    @NotNull
    private int volumen;
    @NotNull
    private  boolean esBajoConsumo;

    protected DispositivoInteligenteTV() {
        
    }

    public DispositivoInteligenteTV(String nombre, double consumoHora, String estado, boolean esBajoConsumo) {
        super(nombre, consumoHora, estado,360,90);
        this.setCanal(12);
        this.setVolumen(25);
        this.esBajoConsumo = esBajoConsumo;
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }
    public void setUsoMaximo(int usoMaximo) {
        this.uso_maximo = usoMaximo;
    }
    public void setUsoMinimo(int usoMinimo) {
        this.uso_minimo = usoMinimo;
    }
}

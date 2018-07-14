package ar.edu.utn.frba.dds.dispositivo;

public class DispositivoInteligenteLuz20W extends DispositivoInteligenteLuz {
    private int temperatura;
    private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteLuz20W(String nombre, String estado) {
        super(nombre, 0.02, estado,true);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

}

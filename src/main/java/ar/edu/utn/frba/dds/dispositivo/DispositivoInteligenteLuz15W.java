package ar.edu.utn.frba.dds.dispositivo;

public class DispositivoInteligenteLuz15W extends DispositivoInteligenteLuz {
    private int temperatura;
    private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteLuz15W(String nombre, String estado) {
        super(nombre, 0.015, estado,true);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

}

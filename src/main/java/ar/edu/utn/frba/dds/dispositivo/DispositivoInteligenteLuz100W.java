package ar.edu.utn.frba.dds.dispositivo;

public class DispositivoInteligenteLuz100W extends DispositivoInteligenteLuz {
    private int temperatura;
    private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteLuz100W(String nombre, String estado) {
        super(nombre, 0.015, estado,false);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

}

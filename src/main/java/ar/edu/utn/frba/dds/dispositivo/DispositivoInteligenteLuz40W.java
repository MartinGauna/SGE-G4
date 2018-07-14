package ar.edu.utn.frba.dds.dispositivo;

public class DispositivoInteligenteLuz40W extends DispositivoInteligenteLuz {
    private int temperatura;
    public int uso_maximo;
    public int uso_minimo;
    private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteLuz40W(String nombre, String estado) {
        super(nombre, 0.04, estado,false);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public int getUso_minimo() {
        return uso_minimo;
    }
    public int getUso_maximo() {
        return uso_maximo;
    }
}

package ar.edu.utn.frba.dds.dispositivo;

public class DispositivoInteligenteVentiladorTecho extends DispositivoInteligenteVentilador {
    private int temperatura;
    public int uso_maximo;
    public int uso_minimo;
    private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteVentiladorTecho(String nombre, String estado) {
        super(nombre, 0.06, estado,true);
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

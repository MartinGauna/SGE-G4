package ar.edu.utn.frba.dds.dispositivo;

public class DispositivoInteligenteAAcondicionado2200 extends DispositivoInteligenteAAcondicionado {
    private int temperatura;
    private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligenteAAcondicionado2200(String nombre,  String estado) {
        super(nombre, 1.613, estado,false);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public int getUso_minimo() {
        return 90;
    }
    public int getUso_maximo() {
        return 360;
    }
}

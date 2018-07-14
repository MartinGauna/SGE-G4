package ar.edu.utn.frba.dds.dispositivo;

public class DispositivoInteligentePCEscritorio extends DispositivoInteligentePC {
    private int temperatura;
    private double consumo;
    private  boolean esBajoConsumo;

    public DispositivoInteligentePCEscritorio(String nombre, String estado) {
        super(nombre, 0.4, estado,true);
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

}

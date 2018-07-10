package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorLuz;

public class DispositivoInteligenteLuz extends DispositivoInteligente {

    private int luminosidad;
    private int uso_maximo;
    private int uso_minimo;
    public DispositivoInteligenteLuz(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado,360,90);
        this.uso_maximo = 360;
        this.uso_minimo = 90;
        ActuadorLuz actuador = new ActuadorLuz(this);
        this.setActuador(actuador);
        this.setLuminosidad(20);
    }

    public int getLuminosidad() {
        return luminosidad;
    }

    public void setLuminosidad(int intensidad) {
        this.luminosidad = intensidad;
    }
}
package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorHeladera;

public class DispositivoInteligenteHeladera extends DispositivoInteligente {

    private int intensidad;

    public DispositivoInteligenteHeladera(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado);
        this.uso_minimo = 90;
        this. uso_maximo = 360;
        ActuadorHeladera actuador = new ActuadorHeladera(this);
        this.setActuador(actuador);
        this.setIntensidad(0);
    }

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }
}
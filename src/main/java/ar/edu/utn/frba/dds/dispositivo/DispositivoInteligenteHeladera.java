package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.actuador.ActuadorHeladera;

public class DispositivoInteligenteHeladera extends DispositivoInteligente {

    private int intensidad;

    public DispositivoInteligenteHeladera(String nombre, int consumoHora, String estado) {
        super(nombre, consumoHora, estado);
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
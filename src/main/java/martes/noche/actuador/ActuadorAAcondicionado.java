package martes.noche.actuador;

import martes.noche.dispositivo.DispositivoInteligenteAAcondicionado;
import martes.noche.Magnitud;
import martes.noche.sensor.Sensor;

public class ActuadorAAcondicionado implements Actuador {

    private DispositivoInteligenteAAcondicionado aire;

    public ActuadorAAcondicionado(DispositivoInteligenteAAcondicionado aire) {
        this.aire = aire;
    }

    public void prenderDispositivo() {
        aire.encender();
    }

    public void apagarDispositivo() {
        aire.apagar();
    }

    public void cambiarModoAAhorro() {
        aire.cambiarAmodoAhorro();
    }

    public void cambiarModoAPrendido() {
        aire.encender();
    }

    public void cambiarModoAApagado() {
        aire.apagar();
    }

    public void subirTemperatura(int cantidad) {
        aire.setTemperatura(aire.getTemperatura() + cantidad);
    }

    public void subirTemperatura(int cantidad, long condicion, Sensor sensor, char criterio ) {
        Magnitud mag = sensor.getMedicion();
        switch (criterio){
            case '=':
                if(condicion == mag.getValor()) {aire.setTemperatura((aire.getTemperatura() + cantidad));}
                break;
            case '>':
                if(condicion > mag.getValor()) {aire.setTemperatura((aire.getTemperatura() + cantidad));}
                break;
            case '<':
                if(condicion < mag.getValor()) {aire.setTemperatura((aire.getTemperatura() + cantidad));}
                break;
        }
    }

    public void bajarTemperatura(int cantidad) {
        aire.setTemperatura(aire.getTemperatura() - cantidad);
    }

}

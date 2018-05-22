package martes.noche.sensor;

import martes.noche.Magnitud;


public abstract class Sensor {
    protected long intervalo;
    protected Magnitud magnitud;

    public Magnitud getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(Magnitud magnitud) {
        this.magnitud = magnitud;
    }

    public long getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(long intervalo) {
        this.intervalo = intervalo;
    }

    public Sensor() {
        setMagnitud(new Magnitud());
        setIntervalo(0);
    }

    public Sensor(long intervalo, String magnitud) {
        setMagnitud(new Magnitud(0, magnitud));
        setIntervalo(intervalo);
    }

    public abstract Magnitud getMedicion();
}

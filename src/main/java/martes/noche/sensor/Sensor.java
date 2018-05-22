package martes.noche.sensor;

import martes.noche.Magnitud;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


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

    public Sensor(long intervalo, String magnitud, int seed) {
        TimerTask task = new TimerTask() {
            public void run() {
                Random rand = new Random(seed);
                Magnitud magnitud = getMagnitud();
                magnitud.setValor(rand.nextLong());
            }
        };
        Timer timer = new Timer("Medir");
        timer.schedule(task, intervalo);
        this.magnitud = new Magnitud(intervalo, magnitud);
    }

    public abstract Magnitud getMedicion();
}

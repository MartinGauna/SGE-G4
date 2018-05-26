package ar.edu.utn.frba.dds.sensor;

import ar.edu.utn.frba.dds.Magnitud;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Sensor {
    protected long intervalo;
    protected Magnitud magnitud;
    public Magnitud mag;

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


    class medirTask extends TimerTask {
        Magnitud m;
        int maxVal;

        public medirTask(Magnitud m, int maxVal){
            this.m = m;
            this.maxVal = maxVal;
        }
        @Override
        public void run() {
            Random rand = new Random();
            int aux = rand.nextInt(this.maxVal);
            this.m.setValor(aux);
        }
    }

    public Sensor() {
        setMagnitud(new Magnitud());
        setIntervalo(0);
    }

    public Sensor(long intervalo, String magnitud, int maxVal) {
        this.intervalo=intervalo;
        this.magnitud = new Magnitud(0, magnitud);
        medirTask task = new medirTask(this.magnitud,maxVal);
        task.run();
        Timer timer = new Timer("Medir");
        timer.schedule(task,0,  intervalo);
    }

    public abstract Magnitud getMedicion();
}

package ar.edu.utn.frba.dds.sensor;

import ar.edu.utn.frba.dds.Magnitud;
import ar.edu.utn.frba.dds.Regla.Observable;
import ar.edu.utn.frba.dds.Regla.Observer;

import java.util.*;


public abstract class Sensor implements Observable {
    protected long intervalo;
    protected Magnitud magnitud;
    public Magnitud mag;

    // Collection de observers
    private Set<Observer> observers;

    public Magnitud getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(Magnitud magnitud) {
        this.magnitud = magnitud;
        // cambio el valor y notifico
        this.notifyObservers();
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
        this.observers = new HashSet();
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

    // agrega un observer a la collection
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    // notifica a todos los observers
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

}

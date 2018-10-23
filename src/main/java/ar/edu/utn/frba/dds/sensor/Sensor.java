package ar.edu.utn.frba.dds.sensor;

import ar.edu.utn.frba.dds.Categoria;
import ar.edu.utn.frba.dds.Magnitud;
import ar.edu.utn.frba.dds.regla.Observable;
import ar.edu.utn.frba.dds.regla.Observer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoSensor")
@Table
public abstract class Sensor implements Observable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private long intervalo;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Magnitud.class)
    @JoinColumn(name = "idMagnitud", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_MAGNITUD"))
    private Magnitud magnitud;

    //public Magnitud mag;
    public Sensor(long intervalo, Magnitud magnitud, Set<Observer> observers) {
        this.intervalo = intervalo;
        this.magnitud = magnitud;
        this.observers = observers;
    }

    // Collection de observers
    @Transient
    private Set<Observer> observers;


    public int getId() {
        return id;
    }

    public Magnitud getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(Magnitud magnitud) {
        this.magnitud = magnitud;
        // cambio el valor y notifico
        //this.notifyObservers();
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

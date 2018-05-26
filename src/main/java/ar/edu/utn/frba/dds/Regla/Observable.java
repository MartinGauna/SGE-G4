package ar.edu.utn.frba.dds.Regla;

public interface Observable {

    public void addObserver(Observer observer);
    public void notifyObservers();

}

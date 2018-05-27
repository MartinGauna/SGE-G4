package ar.edu.utn.frba.dds.regla;

public interface Observable {

    public void addObserver(Observer observer);
    public void notifyObservers();

}

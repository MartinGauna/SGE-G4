package ar.edu.utn.frba.dds.Regla;

import ar.edu.utn.frba.dds.Magnitud;
import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.sensor.Sensor;

import java.lang.reflect.InvocationTargetException;

public class Regla implements Observer{

    protected char criterio;
    protected Long condicion;
    protected int cantidad;
    protected Actuador actuador ;
    protected String methodname;

    public Regla(char criterio, Long condicion, int cantidad, Actuador actuador, String methodname) {
        this.criterio = criterio;
        this.condicion = condicion;
        this.cantidad = cantidad;
        this.actuador = actuador;
        this.methodname = methodname;

    }

    public void update(Observable observable){

        Sensor sensor = (Sensor) observable;
        Magnitud mag = sensor.getMedicion();
        System.out.println("El nuevo valor es: " + mag.getValor());

        switch (criterio){
            case '=':
                if(condicion == mag.getValor())
                {
                    try {
                        actuador.getClass().getDeclaredMethod(methodname).invoke(actuador);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case '>':
                if(condicion > mag.getValor())
                {
                    try {
                        actuador.getClass().getDeclaredMethod(methodname).invoke(actuador);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    ;}
                break;
            case '<':
                if(condicion < mag.getValor())
                {
                    try {
                        actuador.getClass().getDeclaredMethod(methodname).invoke(actuador);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    ;}
                break;
    }
}
}
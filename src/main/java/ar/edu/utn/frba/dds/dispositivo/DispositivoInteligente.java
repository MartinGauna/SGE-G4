package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.actuador.Actuador;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DispositivoInteligente extends Dispositivo {

	List<Consumo> consumo;
    Actuador actuador;
    List<Integer> periodos;
    int uso_minimo;
    int uso_maximo;

    public DispositivoInteligente(String nombre, double consumoHora, String estado) {
        super(nombre, consumoHora, estado);
        this.consumo = new ArrayList<Consumo>();
        this.actuador = null;
        this.initializePeriods();
        this.uso_maximo = 0;
        this.uso_minimo = 0;
    }

    public void addConsumo(int watts, Date fechaInicio, Date fechaFinal) {
        //Create consumo Object
        Consumo consumoI = new Consumo(this, watts, fechaInicio, fechaFinal);

        //Add to Consumo list
        consumo.add(consumoI);

    }

    public void encender() {
        this.getEstado().handleActivo();
    }

    public void apagar() {
        this.getEstado().handleApagado();
    }

    public void cambiarAmodoAhorro() {
        this.getEstado().handleAhorro();
    }

    public Actuador getActuador() {
        return actuador;
    }

    public int getUso_minimo() {
        return uso_minimo;
    }
    public int getUso_maximo() {
        return uso_maximo;
    }

    public void setActuador(Actuador actuador) {
        this.actuador = actuador;
    }

    public int getConsumoTotal(Date fechaInicio, Date fechaFinal) {
        int total = 0;
        for(int i=0; i< consumo.size(); i++) {
            if((consumo.get(i).getFechaInicio().after(fechaInicio) || (consumo.get(i).getFechaFinal()).before(fechaFinal))) {
            	total += consumo.get(i).getWatts();
            }
        }
        return total;
    }

    public void initializePeriods( ) {
    	periodos = new ArrayList<Integer>();
    	for (int i = 0; i < 12; i++) {
    		periodos.add(i, 0);
    	}
    }

    public void cleanPeriod() {
    	Date date = new Date();
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.MONTH, -1);
    	int watts = 0;
    	for(int i = 0; i < consumo.size(); i++) {
    		if(consumo.get(i).getFechaInicio().after(c.getTime())) {
    			watts += consumo.get(i).getWatts();
    		}
    	}
    	periodos.add(c.get(Calendar.MONTH), watts);
    }

    public int getConsumoPeriodo(int mes) {
    	return periodos.get(mes);
    }

}
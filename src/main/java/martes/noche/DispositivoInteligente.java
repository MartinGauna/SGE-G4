package martes.noche;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DispositivoInteligente extends Dispositivo {
    
	List<Consumo> consumo;
    Actuador actuador;
    List<Integer> periodos;

    public DispositivoInteligente(String nombre, int consumoHora, String estado) {
        super(nombre, consumoHora, estado);
        this.consumo = new ArrayList<Consumo>();
        this.actuador = null;
        this.initializePeriods();
    }

    public void addConsumo(int watts, Date fechaInicio, Date fechaFinal) {
        //Create consumo Object
        Consumo consumoI = new Consumo(this, watts, fechaInicio, fechaFinal);

        //Add to Consumo list
        consumo.add(consumoI);

    }
    
    public void encender() {
    	if(!this.getEstado().equals("encendido") ) {
    	    this.setEstado("encendido");
        }
    }
    
    public void apagar() {
        if(!this.getEstado().equals("apagado")) {
            this.setEstado("apagado");
        }
    }

    public void cambiarAmodoAhorro() {
        if(!this.getEstado().equals("ahorro")) {
            this.setEstado("ahorro");
        }
    }

    public Actuador getActuador() {
        return actuador;
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

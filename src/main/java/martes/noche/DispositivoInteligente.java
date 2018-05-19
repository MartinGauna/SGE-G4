package martes.noche;

import java.util.Date;
import java.util.List;

public class DispositivoInteligente extends Dispositivo {
    
	List<Consumo> consumo;
	
	public void addConsumo(int watts, Date fechaInicio, Date fechaFinal) {
        //Create consumo Object
        Consumo consumoI = new Consumo(this, watts, fechaInicio, fechaFinal);

        //Add to Consumo list
        consumo.add(consumoI);

    }
    public int getConsumoTotal(Date periodo) {
        int total = 0;
        for(int i=0; i< consumo.size(); i++) {
        if(consumo.get(i).getFechaInicio().after(periodo)){
        	total += consumo.get(i).getWatts();
        	}
        }
        return total;
    }
    
    public void encender() {
    	this.setEstado("encendido");
    }
    
    public void apagar() {
    	this.setEstado("apagado");
    }

}

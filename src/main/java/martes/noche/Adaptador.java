package martes.noche;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adaptador {

    private Estandard dispositivo;
    private List<Consumo> consumo;
    private Boolean modoAhorro;

    //Constructor
    public Adaptador(Estandard dispositivo) {

        this.consumo = new ArrayList<Consumo>();
    }

    //AgregarConsumo
    public void addConsumo(int watts, Date fechaInicio, Date fechaFinal) {
        //Create consumo Object
        Consumo consumoI = new Consumo(dispositivo, watts, fechaInicio, fechaFinal);

        //Add to Consumo list
        consumo.add(consumoI);

    }

    public int getConsumoTotal(Date fechaInicio, Date fechaFinal) {
        int total = 0;
        for(int i=0; i< consumo.size(); i++) {
            if((consumo.get(i).getFechaInicio().after(fechaInicio) || (consumo.get(i).getFechaFinal()).before(fechaFinal)))
            {total += consumo.get(i).getWatts();}
        }
        return total;
    }

}

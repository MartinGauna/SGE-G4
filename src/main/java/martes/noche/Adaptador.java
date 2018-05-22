package martes.noche;

import java.util.Date;
import java.util.List;

public class Adaptador {

    private Dispositivo dispositivo;
    private List<Consumo> consumo;
    private Boolean modoAhorro;

    //AgregarConsumo
    public void addConsumo(int watts, Date fechaInicio, Date fechaFinal) {
        //Create consumo Object
        Consumo consumoI = new Consumo(dispositivo, watts, fechaInicio, fechaFinal);

        //Add to Consumo list
        consumo.add(consumoI);

    }
    public int getConsumoTotal(Date periodo) {
        int total = 0;
        for(int i=0; i< consumo.size(); i++) {
            if((consumo.get(i).getFechaInicio().before(periodo) || (consumo.get(i).getFechaFinal()).after(periodo)))
            {total += consumo.get(i).getWatts();}
        }
        return total;
    }

}

package martes.noche;

public class Adaptador {

    private Dispositivo dispositivo;
    private List<Consumo> consumo;
    private Bool modoAhorro;

    //AgregarConsumo
    public void addConsumo(int watts, Datetime fechaInicio, Datetime fechaFinal) {
        //Create consumo Object
        Consumo consumoI = new Consumo(dispositivo, watts, fechaInicio, fechaFinal);

        //Add to Consumo list
        consumo.add(consumoI);

    }
    public int getConsumoTotal(Datetime periodo) {
        int total = 0;
        for(int i=0; i< consumo.size(); i++) {
        if(consumo.get(i).fechaInicio <= periodo || consumo.get(i).fechaFinal >= periodo)
        {total += consumo.get(i).watts;}
        }
        return total;
    }

}

package web.models;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;

import java.util.List;

public class HogaresModel {

    private List<Cliente> clientes;
    private List<Long> consumos;

    public List<Long> getConsumos() {
        return consumos;
    }

    public void addConsumo(Long consumo) {
        this.consumos.add(consumo);
    }

    public void setConsumos(List<Long> c){
        this.consumos = c;
    }

    public void setClientes(List<Cliente> c){
        clientes = c;
    }

    public List<Cliente> getClientes (){
        return clientes;
    }
}

package web.models;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;

import java.util.*;

public class HogaresModel {

    private Map<Cliente, Long> map = new HashMap<Cliente, Long>();

    //private List<Cliente> clientes;
    //private List<Long> consumos;

    public Collection<Long> getConsumos() {
        return map.values();
    }
//
//    public void addConsumo(Long consumo) {
//        this.consumos.add(consumo);
//    }
//
//    public void setConsumos(List<Long> c){
//        this.consumos = c;
//    }

    public void addCliente(Cliente c){
        map.put(c, null);
    }


    public Set<Cliente> getClientes (){
        return map.keySet();
    }

    public Map<Cliente, Long> getMap() {
        return map;
    }
}

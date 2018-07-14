package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

import java.util.ArrayList;
import java.util.List;

public class Transformador {

    private List<Cliente> clientes;
    static ArrayList<Transformador> listaDeTransformadores = new ArrayList<Transformador>();


    public Transformador(List<Cliente> clientes)
    {
        this.clientes = clientes;
        listaDeTransformadores.add(this);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    static public ArrayList<Transformador> getAll()
    {
        return listaDeTransformadores;
    }

    public int getConsumoTotal()
    {
        int consumoTotal = 0;
        for (Cliente cliente : clientes) {
           List<Dispositivo> dispositivos = cliente.getDispositivos();
           for(Dispositivo disp : dispositivos)
           {

               consumoTotal += disp.getConsumoTotal();
           }
        }
        return consumoTotal;
    }


}

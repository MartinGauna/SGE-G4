package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

import java.util.List;

public class Transformador {

    private List<Cliente> clientes;


    public Transformador(List<Cliente> clientes)
    {
        this.clientes = clientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
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

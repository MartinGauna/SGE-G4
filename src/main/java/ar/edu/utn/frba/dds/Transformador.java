package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

import java.util.ArrayList;
import java.util.List;

public class Transformador {

    private List<Cliente> clientes;
    static ArrayList<Transformador> listaDeTransformadores = new ArrayList<Transformador>();
    int id;
    double latitud;
    double longitud;
    int idZona;

    public Transformador(int id,   double latitud,double longitud, int idZona)
    {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idZona = idZona;
        listaDeTransformadores.add(this);
    }
    public void asignClientes(List<Cliente> clientes) {
        this.clientes = clientes;
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

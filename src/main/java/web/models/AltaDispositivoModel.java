package web.models;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dispositivo.*;
import web.models.views.DispositivosTable;

import java.util.*;


public class AltaDispositivoModel {

    private DispositivosTable dispositivosDisponibles;
    private List<Cliente> clientes;

    public AltaDispositivoModel() {
        dispositivosDisponibles = new DispositivosTable();
        clientes = new ArrayList<>();
    }

    public List<Dispositivo> getDispositivos() {
        return this.dispositivosDisponibles.getDispositivosDisponibles();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}


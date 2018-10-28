package web.models;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Usuario;

import java.util.ArrayList;
import java.util.List;

public class HogaresModel {

    private List<Cliente> clientes;

//    public List<String> listHogares () {
//
//    }

    public void setClientes(List<Cliente> c){
        clientes = c;
    }

    public List<Cliente> getClientes (){
        return clientes;
    }
}

package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Cliente;

import java.util.List;


public class ClientDao extends BaseDao {

    public ClientDao() {
    }

    public Cliente getCliente(int id) {
        return getById(Cliente.class,id);
    }

    public List<Cliente> list() { return list(Cliente.class); }


}

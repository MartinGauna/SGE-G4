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

    private Cliente clientExists(Cliente e) {
        return clientExists(e.getNumeroDoc());
    }

    private Cliente clientExists(int doc) { return getByPropertyValue(Cliente.class, "numeroDoc", doc); }

    public void addClientIfNotExists(Cliente c){
        Cliente c2 = clientExists(c);
        if (c2 != null){
            update(c2);
        }else{
            save(c);
        }
    }

    public void addClient(Cliente c){
        save(c);
    }

}

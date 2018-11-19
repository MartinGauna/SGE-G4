package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

import java.util.List;


public class ClientDao extends BaseDao {

    DispositivoDao ddao = new DispositivoDao();

    public ClientDao() {
    }

    public Cliente getCliente(int id) {
        return getById(Cliente.class,id);
    }

    public List<Cliente> list() {
        List<Cliente> list = list(Cliente.class);

        List<Dispositivo> dispositivos = ddao.list();

        for (int i = 0; i < dispositivos.size(); i++){
            Dispositivo disp = dispositivos.get(i);
            for (int it = 0; it < list.size(); it ++){
                if(disp.getCliente().getId() == list.get(it).getId()){
                    list.get(it).addDispositivo(disp);
                    break;
                }
            }
        }


        return list; }

    private Cliente clientExists(Cliente e) {
        return clientExists(e.getNumeroDoc());
    }

    private Cliente clientExists(int doc) { return getByPropertyValue(Cliente.class, "numeroDoc", doc); }

    public Cliente clientExists(String username) { return getByPropertyValue(Cliente.class, "user", username); }

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

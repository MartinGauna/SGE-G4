package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Transformador;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

import java.util.ArrayList;
import java.util.List;

public class TransformadorDao extends BaseDao {

    ClientDao cdao = new ClientDao();

    public TransformadorDao() {
    }

    private Transformador transformadorExists(Transformador t) {
        return transformadorExists(t.getId());
    }

    private Transformador transformadorExists(int id) {
        return getByPropertyValue(Transformador.class, "id", id);
    }

    public List<Transformador> list() {
        List<Cliente> listCli = cdao.list();
        List<Transformador> listTra = list(Transformador.class);
        for(int i = 0; i < listTra.size(); i++){
            Transformador trafo = listTra.get(i);
            fillClientTrafo(trafo, listCli);
        }
        return listTra;
    }


    public void addTransformadorIfNotExists(Transformador t){
        Transformador t2 = transformadorExists(t);
        if (t2 != null){
            update(t2);
        }else{
            save(t);
        }
    }

    public void fillClientTrafo(Transformador trafo, List<Cliente> listCliente){
        List<Cliente> listCliNew = new ArrayList<Cliente>();
        List<Cliente> listCliTrafo = trafo.getClientes();
        for (int i = 0; i < listCliTrafo.size(); i++){
            Cliente cliTrafo = listCliTrafo.get(i);
            for(int it = 0; it < listCliente.size(); it++){
                Cliente cli = listCliente.get(it);
                if (cliTrafo.getId() == cli.getId()){
                    listCliNew.add(cli);
                }
            }
        }
        trafo.setClientes(listCliNew);
    }
}

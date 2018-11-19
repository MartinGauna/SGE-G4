package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Transformador;

import java.util.List;

public class TransformadorDao extends BaseDao {

    public TransformadorDao() {
    }

    private Transformador transformadorExists(Transformador t) {
        return transformadorExists(t.getId());
    }

    private Transformador transformadorExists(int id) {
        return getByPropertyValue(Transformador.class, "id", id);
    }

    public List<Transformador> list() { return list(Transformador.class); }


    public void addTransformadorIfNotExists(Transformador t){
        Transformador t2 = transformadorExists(t);
        if (t2 != null){
            update(t2);
        }else{
            save(t);
        }
    }

}

package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Transformador;

public class TransformadorDao extends BaseDao {

    public TransformadorDao() {
    }

    private Transformador transformadorExists(Transformador t) {
        return transformadorExists(t.getId());
    }

    private Transformador transformadorExists(int id) {
        return getByPropertyValue(Transformador.class, "id", id);
    }


    public void addTransformadorIfNotExists(Transformador t){
        Transformador t2 = transformadorExists(t);
        if (t2 != null){
            update(t2);
        }else{
            save(t);
        }
    }

}

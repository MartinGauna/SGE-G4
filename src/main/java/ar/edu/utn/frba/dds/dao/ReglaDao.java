package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.regla.Regla;

import java.util.List;

public class ReglaDao extends BaseDao {

    public List<Regla> getAllReglas(int id) {
        return getListByPropertyValue(Regla.class, "idActuador", id);
    }

    public Regla getReglaByActuadorID(int id) {
        return getByPropertyValue(Regla.class, "idActuador", id);
    }

    public Regla getReglaByID(int id) {
        return getByPropertyValue(Regla.class, "id", id);
    }

    public void addReglaIfNotExists(Regla regla){ save(regla);    }
    public List<Regla> list() {return list(Regla.class);}
}

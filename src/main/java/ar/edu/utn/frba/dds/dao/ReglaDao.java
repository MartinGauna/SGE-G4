package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.regla.Regla;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

import java.util.List;

public class ReglaDao extends BaseDao {

    public List<Regla> getAllReglas(int id) {
        return getListByPropertyValue(Regla.class, "idActuador", id);
    }
}

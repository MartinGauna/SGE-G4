package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.regla.Condicion;
import ar.edu.utn.frba.dds.regla.Regla;

import java.util.List;

public class CondicionDao extends BaseDao{

    public List<Condicion> getCondiciones(Regla regla) {
        return getListByPropertyValue(Condicion.class, "idRegla", regla.getId());
    }



}

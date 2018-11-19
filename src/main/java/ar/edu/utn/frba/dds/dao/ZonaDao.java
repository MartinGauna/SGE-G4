package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Zona;

public class ZonaDao extends BaseDao {

    public ZonaDao() {
    }

    private Zona zonaExists(Zona z) {
        return zonaExists(z.getNombreDescriptivo());
    }

    private Zona zonaExists(String nombre) {
        return getByPropertyValue(Zona.class, "nombreDescriptivo", nombre);
    }


    public void addZonaIfNotExists(Zona z){
        Zona z2 = zonaExists(z);
        if (z2 != null){
            update(z2);
        }else{
            save(z);
        }
    }

}

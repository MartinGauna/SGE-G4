package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

import java.util.List;

public class DispositivoDao extends BaseDao{

    public List<Dispositivo> getAllDI(Cliente user) {
        return getListByPropertyValue(Dispositivo.class, "idCliente", user);
    }

    private Dispositivo dispositivoExists(Dispositivo e) {
        return dispositivoExists(e.getNombre());
    }

    private Dispositivo dispositivoExists(String nombre) { return getByPropertyValue(Dispositivo.class, "nombre", nombre); }


    public void addDispositivoIfNotExists(Dispositivo d){
        Dispositivo d2 = dispositivoExists(d);
        if (d2 != null){
            update(d2);
        }else{
            save(d);
        }
    }
}

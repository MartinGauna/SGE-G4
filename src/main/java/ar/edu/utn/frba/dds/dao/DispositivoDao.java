package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;

public class DispositivoDao extends BaseDao{

    public List<Dispositivo> getAllDI(Cliente user) {
        return getListByPropertyValue(Dispositivo.class, "idCliente", user);
    }

    public List<Dispositivo> list() {return list(Dispositivo.class);}

    private Dispositivo dispositivoExists(Dispositivo e) {
        return dispositivoExists(e.getNombre());
    }

    private Dispositivo dispositivoExists(String nombre) { return getByPropertyValue(Dispositivo.class, "nombre", nombre); }


    public void addDispositivoIfNotExists(Dispositivo d){
        Dispositivo d2 = dispositivoExists(d);
        if (d2 != null){
            update(d2);
        }else{
            if( d instanceof DispositivoInteligente){
                Actuador a = ((DispositivoInteligente) d).getActuador();
                persistList(new ArrayList<Object>() {{add(a); add(d);}});
            }
            else {
                save(d);
            }
        }
    }
}

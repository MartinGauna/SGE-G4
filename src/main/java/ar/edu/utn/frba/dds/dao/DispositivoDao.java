package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.sensor.Sensor;

import java.util.ArrayList;
import java.util.List;

public class DispositivoDao extends BaseDao{

    public List<DispositivoInteligente> getAllDI(Cliente user) {
        return getListByPropertyValue(DispositivoInteligente.class, "idCliente", user);
    }

    public List<Dispositivo> getAllDispositivos(Cliente user) {
        return getListByPropertyValue(Dispositivo.class, "idCliente", user);
    }

    public List<Dispositivo> list() {return list(Dispositivo.class);}

    private Dispositivo dispositivoExists(Dispositivo e) {
        return dispositivoExists(e.getNombre());
    }

    private Dispositivo dispositivoExists(String nombre) { return getByPropertyValue(Dispositivo.class, "nombre", nombre); }

    public Dispositivo getDispositivo(int id) {
        return getById(Dispositivo.class,id);
    }


    public DispositivoInteligente getDI(int id) {
        return getById(DispositivoInteligente.class,id);
    }

    public void addDispositivoIfNotExists(Object d){
        Dispositivo d2 = dispositivoExists((Dispositivo)d);
        if (d2 != null){
            update(d2);
        }else{
            save(d);
        }
    }

    public void addDispositivoInteligenteIfNotExists(DispositivoInteligente d) {
        Dispositivo d2 = dispositivoExists((Dispositivo) d);
        if (d2 != null) {
            update(d2);
        } else {
            List topersist = new ArrayList();

            if (!d.getSensores().isEmpty()) {
                for(Sensor s: d.getSensores()) {
                    topersist.add(s.getMagnitud());
                    topersist.add(s);
                }
            }

            topersist.add(d);

            persistList(topersist);
            save(d);
        }
    }
}

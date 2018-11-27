package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

import java.util.List;

public class ActuadorDao extends BaseDao{

    public <T> List<T> getAllActuadores(Class<T> clazz, Object idDispositivo) {
        // se debe enviar como parametros la clase del actuador (ActuadorTV, ActuadorLuxHeladera,)
        // y el id del dispositivo al cual pertenece el actuador

        return getListByPropertyValue(clazz, "idDispositivo", idDispositivo);
    }

    public void addActuadorIfNotExists(Actuador d){
        Actuador d2 = actuadorExists(d);
        if (d2 != null){
            update(d2);
        }else{
            save(d);
        }
    }

    private Actuador actuadorExists(Actuador d) {
        return actuadorExists(d.getId());
    }

    private Actuador actuadorExists(int id) {
        return getByPropertyValue(Actuador.class, "id", id); }

}

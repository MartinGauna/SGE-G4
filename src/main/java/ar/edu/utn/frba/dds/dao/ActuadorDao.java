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
}

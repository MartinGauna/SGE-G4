package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

import java.util.ArrayList;
import java.util.List;

public class DispositivoDao extends BaseDao{

    public List<Dispositivo> getAllDI(Cliente user) {
        return getListByPropertyValue(Dispositivo.class, "idCliente", user);
    }
}

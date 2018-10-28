package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

import java.util.List;

public class ConsumoDao extends BaseDao{

    public List<Consumo> getConsumo(Dispositivo disp) {
        return getListByPropertyValue(Consumo.class, "idDispositivo", disp);
    }
}

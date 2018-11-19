package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.models.QueryModel;

import java.util.Date;
import java.util.List;

public class ConsumoDao extends BaseDao{

    public List<Consumo> getConsumo(Dispositivo disp) {
        return getListByPropertyValue(Consumo.class, "idDispositivo", disp);
    }

    public <T> List<T> getConsumosByPeriod(Date fi, Date ff) {
        List<T> result = findByQuery(new QueryModel().setQuery(
                "SELECT o FROM Consumo" + " o WHERE fechaInicio" +" >= " + fi + "and fechaFinal <= " + ff + "; ")
                .setParamValues(new Object[] {fi, ff}));

        return result;
    }

    private Consumo consumoExists(Consumo e) {
        return consumoExists(e.getId());
    }

    private Consumo consumoExists(int id) { return getByPropertyValue(Consumo.class, "id", id); }


    public void addConsumoIfNotExists(Consumo d){
        Consumo d2 = consumoExists(d);
        if (d2 != null){
            update(d2);
        }else{
            save(d);
        }
    }

}

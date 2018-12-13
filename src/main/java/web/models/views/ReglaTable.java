package web.models.views;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.ReglaDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.regla.Regla;

public class ReglaTable {
    private String dispositivo ;
    private String accion;

    public ReglaTable() {
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }
    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getAccion() {
        return accion;
    }

}

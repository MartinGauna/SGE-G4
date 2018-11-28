package web.models.views;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.ReglaDao;
import ar.edu.utn.frba.dds.regla.Regla;

public class ReglaTable {
    private Regla regla;
    private String accion;
    private int actuadorID ;
    ReglaDao rdao = new ReglaDao();
    private int reglaID;

    public ReglaTable(Cliente cliente) {
        //this.regla = rdao.getAllReglas(cliente.getId());
        //this.estado = estado;
    }

    public ReglaTable() {
    }

    public void setReglaID(int id) {
        this.reglaID = id;
    }
    public void setAccion(String accion) {
        this.accion = accion;
    }
    public void setActuadorID(int actuadorID) {
        this.actuadorID = actuadorID;
    }


}

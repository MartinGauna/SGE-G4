package web.models;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import web.models.views.HogaresTable;

import java.util.*;

public class HogaresModel {

    private List<HogaresTable> table;

    public HogaresModel() {
        this.table = new ArrayList<HogaresTable>();
    }

    public HogaresModel(List<HogaresTable> table) {
        this.table = table;
    }

    public List<HogaresTable> getTable() {
        return table;
    }

    public void setTable(List<HogaresTable> table) {
        this.table = table;
    }
}

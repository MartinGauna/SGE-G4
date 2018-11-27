package web.models;

import web.models.views.EstadoDispositivosTable;
import web.models.views.HogaresTable;

import java.util.ArrayList;
import java.util.List;

public class EstadoDispositivosModel {
    private List<EstadoDispositivosTable> table;

    public EstadoDispositivosModel() {
        this.table = new ArrayList<EstadoDispositivosTable>();
    }

    public EstadoDispositivosModel(List<EstadoDispositivosTable> table) {
        this.table = table;
    }

    public List<EstadoDispositivosTable> getTable() {
        return table;
    }

    public void setTable(List<EstadoDispositivosTable> table) {
        this.table = table;
    }
}


package web.models;

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

package web.models;

import web.models.views.SimplexTable;

import java.util.ArrayList;
import java.util.List;

public class SimplexModel {


    //indicesSimplex
    private List<SimplexTable> table;

    /*=== GETTERS AND SETTERS ===*/


    public SimplexModel() {
        table = new ArrayList<>();

    }

    public SimplexModel(List<SimplexTable> tableN) {
        this.table = tableN;
    }

    public void setSimplexTable(List<SimplexTable> tableSimplex) {
        this.table = tableSimplex;
    }

    public List<SimplexTable> getTable() {
        return table;
    }
}
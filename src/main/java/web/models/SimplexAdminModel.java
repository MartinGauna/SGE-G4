package web.models;

import web.models.views.SimplexTable;

import java.util.ArrayList;
import java.util.List;

public class SimplexAdminModel {

    private boolean prendido;
    private int intervalo;

    /*=== GETTERS AND SETTERS ===*/

    public SimplexAdminModel() {}

    public void setPrendido(boolean prendido) {
        this.prendido = prendido;
    }
    public boolean getPrendido() {
        return prendido;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }
    public int getIntervalo() {
        return intervalo;
    }


}
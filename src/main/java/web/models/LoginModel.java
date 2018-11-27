package web.models;

import web.models.views.LoginTable;

import java.util.ArrayList;
import java.util.List;

public class LoginModel {

    private List<LoginTable> table;

    public LoginModel() {
        this.table = new ArrayList<LoginTable>();
    }

    public LoginModel(List<LoginTable> table) {
        this.table = table;
    }

    public List<LoginTable> getTable() {
        return table;
    }

    public void setTable(List<LoginTable> table) {
        this.table = table;
    }
}

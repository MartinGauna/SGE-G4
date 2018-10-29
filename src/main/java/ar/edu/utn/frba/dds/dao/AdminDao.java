package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Administrador;
import ar.edu.utn.frba.dds.Cliente;

import java.util.List;


public class AdminDao extends BaseDao {

    public AdminDao() {
    }

    public Administrador getAdmin(int id) {
        return getById(Administrador.class,id);
    }

    public List<Administrador> list() { return list(Administrador.class); }

    private Administrador adminExists(Administrador a) {
        return adminExists(a.getUsername());
    }

    public Administrador adminExists(String username) { return getByPropertyValue(Administrador.class, "user", username); }

    public void addAdminIfNotExists(Administrador c){
        Administrador c2 = adminExists(c);
        if (c2 != null){
            update(c2);
        }else{
            save(c);
        }
    }

    public void addadmin(Administrador c){
        save(c);
    }

}

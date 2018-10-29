package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Administrador;
import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Usuario;
import java.util.List;

public class UsuarioDao extends BaseDao {

    AdminDao adao = new AdminDao();

    public UsuarioDao() {
    }

    public Usuario getUser(String username) {
        return getByPropertyValue(Cliente.class, "user", username);
    }

    public Administrador isAdmin(Usuario user) {
                    Administrador admin = adao.adminExists(user.getUsername());
        return admin;
    }

    public List<Usuario> list() { return list(Usuario.class); }

}

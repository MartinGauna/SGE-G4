package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Usuario;

import java.util.List;

public class UsuarioDao extends BaseDao {

    ClientDao cdao = new ClientDao();
    AdminDao adao = new AdminDao ();

    public UsuarioDao() {
    }

    /**public <T> T getUser(String username) {
        Cliente c = getByPropertyValue(Cliente.class, "user", username);

        if (c == null) {
            Administrador a = getByPropertyValue(Administrador.class, "user", username);
            return (T) a;
        } else {
            return (T) c;
        }
    }**/

    public Usuario getUser(String username) {
        return getByPropertyValue(Cliente.class, "user", username);
    }


    public Cliente isClient(Usuario user) {
            Cliente c = cdao.clientExists(user.getUsername());
        return c;
    }

    public List<Usuario> list() { return list(Usuario.class); }

}

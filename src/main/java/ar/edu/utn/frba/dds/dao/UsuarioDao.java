package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Usuario;

import java.util.List;

public class UsuarioDao extends BaseDao {

    ClientDao cdao = new ClientDao();

    public UsuarioDao() {
    }

    public Usuario getUser(String username) {
            return getByPropertyValue(Usuario.class, "user", username);
        }

    public Cliente isClient(Usuario user) {
            Cliente c = cdao.clientExists(user.getUsername());
        return c;
    }

    public List<Usuario> list() { return list(Usuario.class); }

}

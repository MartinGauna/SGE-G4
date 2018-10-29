package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Usuario;

import java.util.List;

public class UsuarioDao extends BaseDao {

    public UsuarioDao() {
    }

    public Usuario getUsuario(int id) {
        return  getUsuario(id);
    }

    public List<Usuario> list() { return list(Usuario.class); }

}

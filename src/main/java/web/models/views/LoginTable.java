package web.models.views;

import ar.edu.utn.frba.dds.Usuario;

public class LoginTable {

    private Usuario usuario;
    private String contraseña;

    public LoginTable(Usuario usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public LoginTable() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}

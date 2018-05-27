package ar.edu.utn.frba.dds;

import java.time.LocalDate;

public abstract class Usuario {
    private String nombre;
    private String apellido;
    private String domicilio;
    private String user;
    private String password;
    private LocalDate fechaAlta;

    // Nombre
    public String getNombre() {

        return nombre;
    }
    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public Usuario(String nombre, String apellido, String domicilio, String user, String password, LocalDate fechaAlta){
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.user = user;
        this.password = password;
        this.fechaAlta = fechaAlta;
    }

    // Apellido
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Domicilio
    public String getDomicilio() {

        return domicilio;
    }
    public void setDomicilio(String domicilio) {

        this.domicilio = domicilio;
    }

    // User
    public String getUsername() {

        return user;
    }
    public void setUsername(String username) {

        this.user = username;
    }

    // Password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Fecha de alta al sistema
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }
    public void setFechaAlta(LocalDate fecha) {
        fechaAlta = fecha;
    }

}

package ar.edu.utn.frba.dds;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private String domicilio;
    @NotNull
    private String user;

    private String password;
    @NotNull
    private LocalDate fechaAlta;

    protected Usuario() {
    }

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

    public int getId() {
        return id;
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


    // Session del usuario.

    //genero una session: 'id-name'
    public String userSession() {
        return getId() + "-" + getUsername();
    }
    // retorna el id en base a una userSession
    public int getSessionId(String userSession){
        return Integer.parseInt(userSession.substring(0,userSession.indexOf("-")));
    }
}

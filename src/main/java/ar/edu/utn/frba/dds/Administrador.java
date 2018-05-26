package ar.edu.utn.frba.dds;

import java.time.LocalDate;
import java.time.Period;

public class Administrador extends Usuario {

    private int legajo;


    public Administrador(String nombre, String apellido, String domicilio, String user, String password, LocalDate fechaAlta, int legajo) {
        super(nombre, apellido, domicilio, user,password,fechaAlta);
        this.legajo = legajo;
    }

    // Legajo
    public int getLegajo() {
        return legajo;
    }
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public long getAntiguedad() {
        LocalDate now = LocalDate.now();
        return (Period.between(getFechaAlta().withDayOfMonth(1),
                now.withDayOfMonth(1))).toTotalMonths();
    }

    @Override
    public String toString() {
        return  "Administrador: \n"+
                "\tNombre: " + getNombre() + "\n" +
                "\tApellido: " + getApellido() + "\n" +
                "\tDomicilio: " + getDomicilio() + "\n" +
                "\tUser: " + getUsername() + "\n" +
                "\tPassword: " + getPassword() + "\n" +
                "\tFecha de Alta: " + getFechaAlta() + "\n" +
                "\tLegajo: " + getLegajo() + "\n";
    }
}

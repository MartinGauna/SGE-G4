package martes.noche;


import java.time.LocalDate;
import java.time.Period;

public class Administrador extends Persona {

    private LocalDate fechaAlta;
    private int legajo;

    public Administrador(LocalDate fechaAlta, int legajo) {
        this.fechaAlta = fechaAlta;
        this.legajo = legajo;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public long getAntiguedad() {
        LocalDate now = LocalDate.now();
        return (Period.between(now.withDayOfMonth(1),
                getFechaAlta().withDayOfMonth(1))).toTotalMonths();
    }
}

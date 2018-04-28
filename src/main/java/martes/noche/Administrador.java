package martes.noche;


import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

public class Administrador extends Usuario {

    private int legajo;

    public Administrador(LocalDate fechaAlta, int legajo) {
        this.setFechaAlta(fechaAlta);
        this.legajo = legajo;
    }

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
}

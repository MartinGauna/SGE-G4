package martes.noche;


import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
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
        return (Period.between(getFechaAlta().withDayOfMonth(1),
                now.withDayOfMonth(1))).toTotalMonths();
    }

    public void loadJSON(String path) throws IOException {

        File file = new File(path);
        String content = FileUtils.readFileToString(file, "utf-8");
        JSONObject dispJsonObject = new JSONObject(content);

        this.fechaAlta = LocalDate.parse(dispJsonObject.getString("nombre"));
        this.legajo = dispJsonObject.getInt("legajo");
    }
}

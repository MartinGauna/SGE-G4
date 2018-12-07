package web.models.views;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

public class MedicionesTable {
    private String dispositivo;
    private String variable;
    private long magnitud;
    private String unidad;

    public MedicionesTable(Dispositivo dispositivo, String variable, long magnitud, String unidad) {
        this.dispositivo = dispositivo.getNombre();
        this.magnitud = magnitud;
        this.unidad = unidad;
        this.variable = variable;
    }

    public MedicionesTable() {
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public long getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(long magnitud) {
        this.magnitud = magnitud;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }
}

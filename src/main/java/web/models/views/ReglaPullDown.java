package web.models.views;

public class ReglaPullDown {
    private int id;
    private String dispositivo ;
    private String accion;
    private int cantidad;
    private long valorCondicion;
    private String sensor;
    private char condicion;

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public char getCondicion() {
        return condicion;
    }

    public void setCondicion(char condicion) {
        this.condicion = condicion;
    }

    public ReglaPullDown() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getAccion() {
        return accion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public long getValorCondicion() {
        return valorCondicion;
    }

    public void setValorCondicion(long valorCondicion) {
        this.valorCondicion = valorCondicion;
    }

}

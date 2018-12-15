package web.models.views;

public class SimplexTable {

    private String dispositivo;
    private String index;

    public SimplexTable(String dispositivo, String index) {
        this.dispositivo = dispositivo;
        this.index = index;
    }

    public SimplexTable() {
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public String getIndex() {
        return index;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public void setIndex(String index) {
        this.index = index;
    }

}

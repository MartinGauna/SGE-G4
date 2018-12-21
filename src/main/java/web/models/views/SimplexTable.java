package web.models.views;

public class SimplexTable {

    private String dispositivo;
    private int index;

    public SimplexTable(String dispositivo, int index) {
        this.dispositivo = dispositivo;
        this.index = index;
    }

    public SimplexTable() {
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public int getIndex() {
        return index;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}

package web.models.views;

public class SimplexTable {

    private String dispositivo;
    private String indexSimplex;

    public SimplexTable(String dispositivo, String index) {
        this.dispositivo = dispositivo;
        this.indexSimplex = index;
    }

    public SimplexTable() {
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public String getSimplexIndex() {
        return indexSimplex;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public void setSimplexindex(double index) {
        this.indexSimplex = index;
    }

}

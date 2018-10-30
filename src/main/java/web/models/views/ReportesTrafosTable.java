package web.models.views;

import ar.edu.utn.frba.dds.ReporteTransformador;

public class ReportesTrafosTable {
    public ReporteTransformador getReporteTrafo() {
        return reporte;
    }

    public void setReporteTrafo(ReporteTransformador reporte) {
        this.reporte = reporte;
    }

    private ReporteTransformador reporte;

    public ReportesTrafosTable(ReporteTransformador r) {
        this.reporte = r;
    }

    public ReportesTrafosTable(){}


}

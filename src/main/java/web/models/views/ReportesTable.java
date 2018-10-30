package web.models.views;

import ar.edu.utn.frba.dds.Reporte;

public class ReportesTable {
    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    private Reporte reporte;

    public ReportesTable(Reporte r) {
        this.reporte = r;
    }

    public ReportesTable(){}
}

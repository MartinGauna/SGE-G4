package web.models;

import web.models.views.ReportesTable;
import web.models.views.ReportesTrafosTable;

import java.util.ArrayList;
import java.util.List;

public class ReportesModel {
    private List<ReportesTable> tableReporte;
    private List<ReportesTrafosTable> tableReporteTrafo;

    public ReportesModel() {
        this.tableReporte = new ArrayList<ReportesTable>();
        this.tableReporteTrafo = new ArrayList<ReportesTrafosTable>();
    }

    public ReportesModel(List<ReportesTable> tableReporte, List<ReportesTrafosTable> tableReporteTrafo) {
        this.tableReporte = tableReporte;
        this.tableReporteTrafo = tableReporteTrafo;
    }

    public List<ReportesTable> getTableReporte() {
        return tableReporte;
    }
    public List<ReportesTrafosTable> getTableReporteTrafo() {
        return tableReporteTrafo;
    }

    public void setTableReporte(List<ReportesTable> table) {
        this.tableReporte = table;
    }
    public void setTableReporteTrafo(List<ReportesTrafosTable> table) {
        this.tableReporteTrafo = table;
    }

    public void cleanTableReporte(){
        this.tableReporte.clear();
    }

    public void cleanTableReporteTrafo(){
        this.tableReporteTrafo.clear();
    }
}

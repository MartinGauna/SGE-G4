package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Reporte;
import ar.edu.utn.frba.dds.ReporteTransformador;


import java.util.List;

public class ReporteDao extends BaseDao{

    public List<Reporte> getReportePorCliente(int idCliente) {return getListByPropertyValue(Reporte.class, "clienteId", idCliente); }

    public List<ReporteTransformador> getReportePorTransformador(int idTrafo) {
        return getListByPropertyValue(ReporteTransformador.class, "transformadorId", idTrafo); }

    public List<Reporte> getReportePorTipo(int tipo) {
        return getListByPropertyValue(Reporte.class, "type", tipo);
    }

    public List<Reporte> listReporte() { return list(Reporte.class); }

    public List<ReporteTransformador> listReporteTrafo() { return list(ReporteTransformador.class); }

    public void addReporte(Reporte c){
        save(c);
    }

    public void addReporteTrafo(ReporteTransformador c){
        save(c);
    }

}

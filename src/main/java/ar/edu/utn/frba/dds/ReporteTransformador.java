package ar.edu.utn.frba.dds;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table
public class ReporteTransformador {

    public int getId() {
        return id;
    }

    public String getReporte() {
        return reporte;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public int getTransformadorId() {
        return transformadorId;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @NotNull
    public String reporte;

    @NotNull
    public Date fechaInicio;

    @NotNull
    public Date fechaFin;

    @NotNull
    public int transformadorId;

    public ReporteTransformador(){}

    public ReporteTransformador(int transformadorId, Date fechaInicio, Date fechaFin, String reporte){
        this.transformadorId = transformadorId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.reporte = reporte;
    }
}
package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dao.ClientDao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table
public class Reporte {

    public int getId() {
        return id;
    }

    public int getClienteId() {
        return clienteId;
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

    public int getType() {
        return type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @NotNull
    public int clienteId;

    @NotNull
    public String reporte;

    @NotNull
    public Date fechaInicio;

    @NotNull
    public Date fechaFin;

    @NotNull
    public int type;

    @Transient
    public String clienteName;

    @Transient
    ClientDao cdao = new ClientDao();

    public Reporte(){}

    public Reporte(int clienteId, Date fechaInicio, Date fechaFin, String reporte, int type){
        this.clienteId = clienteId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.reporte = reporte;
        this.type = type;
        this.clienteName = cdao.getCliente(clienteId).getNombre() + ' ' + cdao.getCliente(clienteId).getApellido();
    }

    public void setClienteName() {
        this.clienteName = cdao.getCliente(clienteId).getNombre() + ' ' + cdao.getCliente(clienteId).getApellido();
    }
}

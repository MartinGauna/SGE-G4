package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dao.TransformadorDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.Zona;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Transformador {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;


    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idTransformador", referencedColumnName = "id")
    private List<Cliente> clientes;

    @NotNull
    double latitud;
    @NotNull
    double longitud;

    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = Zona.class)
    @JoinColumn(name = "idZona", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ZONA"))
    private Zona zona;

    public Transformador(double latitud, double longitud, Zona zona) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.zona = zona;
        this.clientes = new ArrayList<Cliente>();
    }

    public Transformador() {
    }

    public void asignClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void addCliente(Cliente cliente) {
        if (this.clientes == null ) {
            this.clientes = new ArrayList<Cliente>();
        }
        this.clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }


    public int getConsumoTotal() {
        int consumoTotal = 0;
        for (Cliente cliente : clientes) {
            List<Dispositivo> dispositivos = cliente.getDispositivos();
            if(dispositivos != null) {
                for (Dispositivo disp : dispositivos) {

                    consumoTotal += disp.getConsumoTotal();
                }
            }
        }
        return consumoTotal;
    }

    public int getConsumoTotal(Date fechaInicio, Date fechaFin) {
        int consumoTotal = 0;
        TransformadorDao tdao = new TransformadorDao();
        tdao.fillClientTrafo(this,this.clientes);
        for (Cliente cliente : clientes) {
            List<Dispositivo> dispositivos = cliente.getDispositivos();
            if(dispositivos != null) {
                for (Dispositivo disp : dispositivos) {

                    consumoTotal += disp.getConsumoTotal(fechaInicio, fechaFin);
                }
            }
        }
        return consumoTotal;
    }

    public int getId() {
        return id;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void fetchClientes(){

    }
}

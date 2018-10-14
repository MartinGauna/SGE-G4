package ar.edu.utn.frba.dds;

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

    static ArrayList<Transformador> listaDeTransformadores = new ArrayList<Transformador>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idTransformador", referencedColumnName = "id")
    private List<Cliente> clientes;

    @NotNull
    double latitud;
    @NotNull
    double longitud;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Zona.class)
    @JoinColumn(name = "idZona", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ZONA"))
    private Zona zona;

    public Transformador(double latitud, double longitud, Zona zona) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.zona = zona;
        this.clientes = new ArrayList<Cliente>();
        listaDeTransformadores.add(this);
    }

    public Transformador() {
    }

    public void asignClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    static public ArrayList<Transformador> getAll() {
        return listaDeTransformadores;
    }

    public int getConsumoTotal() {
        int consumoTotal = 0;
        for (Cliente cliente : clientes) {
            List<Dispositivo> dispositivos = cliente.getDispositivos();
            for (Dispositivo disp : dispositivos) {

                consumoTotal += disp.getConsumoTotal();
            }
        }
        return consumoTotal;
    }

    public int getConsumoTotal(Date fechaInicio, Date fechaFin) {
        int consumoTotal = 0;
        for (Cliente cliente : clientes) {
            List<Dispositivo> dispositivos = cliente.getDispositivos();
            for (Dispositivo disp : dispositivos) {

                consumoTotal += disp.getConsumoTotal(fechaInicio, fechaFin);
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
}

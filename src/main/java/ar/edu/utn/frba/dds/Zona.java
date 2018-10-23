package ar.edu.utn.frba.dds;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idZona", referencedColumnName = "id")
    private List<Transformador> transformadores;

    @NotNull
    int metrosRedonda;

    @NotNull
    private String nombreDescriptivo;

    @NotNull
    private double latitud;

    @NotNull
    private double longitud;

    public Zona(String nombreDescriptivo, double latitud, double longitud, int metrosRedonda)
    {
        this.metrosRedonda = metrosRedonda;
        this.transformadores = transformadores;
        this.nombreDescriptivo = nombreDescriptivo;
        this.transformadores = new ArrayList<Transformador>();
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Zona() {
    }

    public int getMetrosRedonda() {
        return metrosRedonda;
    }
    public String getNombreDescriptivo() {
        return nombreDescriptivo;
    }
    public double getLatitud() {
        return latitud;
    }
    public double getLongitud() { return longitud;}
    public List<Transformador> getTransformadores() {
        return transformadores;
    }

    public int getId() {
        return id;
    }

    public int getConsumoTotal()
    {
        int consumoTotal = 0;
        for (Transformador transformador : transformadores) {
            consumoTotal += transformador.getConsumoTotal();
        }
        return consumoTotal;
    }

    public void addTransformador(Transformador tra) {
        this.transformadores.add(tra);
    }

}

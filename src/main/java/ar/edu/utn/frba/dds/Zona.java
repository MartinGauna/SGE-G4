package ar.edu.utn.frba.dds;

import java.util.List;

public class Zona {
    private List<Transformador> transformadores;
    int metrosRedonda;
    private String nombreDescriptivo;
    private float latitud;
    private float longitud;

    public Zona(String nombreDescriptivo, float latitud, float longitud, List<Transformador> transformadores, int metrosRedonda)
    {
        this.metrosRedonda = metrosRedonda;
        this.transformadores = transformadores;
        this.nombreDescriptivo = nombreDescriptivo;
        this.latitud = latitud;
        this.longitud = longitud;
    }


    public int getMetrosRedonda() {
        return metrosRedonda;
    }
    public String getNombreDescriptivo() {
        return nombreDescriptivo;
    }
    public float getLatitud() {
        return latitud;
    }
    public float getLongitud() { return longitud;}
    public List<Transformador> getTransformadores() {
        return transformadores;
    }

    public int getConsumoTotal()
    {
        int consumoTotal = 0;
        for (Transformador transformador : transformadores) {
            consumoTotal += transformador.getConsumoTotal();
        }
        return consumoTotal;
    }

}
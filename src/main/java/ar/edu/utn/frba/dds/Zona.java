package ar.edu.utn.frba.dds;

import java.util.List;

public class Zona {
    private List<Transformador> transformadores;
    int metrosRedonda;

    public Zona(List<Transformador> transformadores, int metrosRedonda)
    {
        this.metrosRedonda = metrosRedonda;
        this.transformadores = transformadores
    }
    public int getMetrosRedonda() {
        return metrosRedonda;
    }
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

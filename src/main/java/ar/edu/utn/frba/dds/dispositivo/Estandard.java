package ar.edu.utn.frba.dds.dispositivo;

public class Estandard extends Dispositivo {

    //Class Properties
    //Note: Dispositivo properties (inherating): String nombre, int consumoHora,  String estado;

    Adaptador adaptador;

    public Estandard(String nombre, int consumoEstimado, String estado) {
        super(nombre, consumoEstimado, estado);
    }

    public void convertir(Adaptador adaptador) {
        this.adaptador = adaptador;
    }

    public Adaptador getAdaptador() {
        return adaptador;
    }

    public void setAdaptador(Adaptador adaptador) {
        this.adaptador = adaptador;
    }

}

package martes.noche;

import java.util.ArrayList;

public class Estandard extends Dispositivo {

    //Class Properties
    //Note: Dispositivo properties (inherating): String nombre, int consumoHora,  String estado;

    Adaptador adaptador;

    public Estandard(String nombre, int consumoEstimado, String estado) {
        super(nombre, consumoEstimado, estado);
    }

    public void convertir(Adaptador adaptador){
        this.adaptador = adaptador;
    }

}

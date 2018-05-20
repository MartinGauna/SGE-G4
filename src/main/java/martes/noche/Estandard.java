package martes.noche;

public class Estandard extends Dispositivo {

    //Class Properties
    //Note: Dispositivo properties (inherating): String nombre, int consumoHora,  String estado;

    public int consumoEstimado;

    public void setConsumoEstimado(int consumoEstimado) {
        this.consumoEstimado = consumoEstimado;
    }

}

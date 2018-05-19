package martes.noche;

public class Consumo {

    private Dispositivo dispositivo;
    private int watts;
    private Datetime fechaInicio;
    private Datetime fechaFinal;

    public Consumo(Dispositivo dispositivo, int watts, Datetime fechaInicio, Datetime fechaFinal) {
        this.dispositivo = dispositivo;
        this.watts = watts;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

}

package martes.noche;

import java.lang.String;

public class Dispositivo {

    private String nombre;
    private int consumoHora;
    private String estado;

    public enum Estados {
        ENCENDIDO("encendido"),
        APAGADO("apagado"),
    	AHORRO("ahorro");

        private final String value;


        Estados(final String text) {
            this.value = text;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    // Dispositivo
    public Dispositivo(){
        this.nombre = "";
        this.consumoHora = 0;
        this.estado = "";
    }
    public Dispositivo(String nombre, int consumoHora, String estado) {
        this.nombre = nombre;
        this.consumoHora = consumoHora;
        this.estado = estado;
    }

    // Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Consumo por Hora
    public int getConsumoHora() {
        return consumoHora;
    }
    public void setConsumoHora(int consumoHora) {
        this.consumoHora = consumoHora;
    }

    // Estado del dispositivo.
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        // TODO Preguntar: Si mando un estado incorrecto, debo tirar exeption?
        if(estado.equals(Estados.ENCENDIDO.toString()) || estado.equals(Estados.APAGADO.toString()) || estado.equals(Estados.AHORRO.toString())){
            this.estado = estado;
        }
    }

    @Override
    public String toString() {
        return  "Dispositivo: \n"+
                "\tNombre: " + getNombre() + "\n" +
                "\tConsumo por Hora: " + getConsumoHora() + "\n" +
                "\tEstado: " + getEstado() + "\n";
    }
}

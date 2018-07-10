package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.dispositivo.estadosDispositivo.*;

import java.lang.String;

public class Dispositivo {

    private String nombre;
    private double consumoHora;
    private Context estado;

//    public enum Estados {
//        ENCENDIDO("encendido"),
//        APAGADO("apagado"),
//    	AHORRO("ahorro");
//
//        private final String value;
//
//        Estados(final String text) {
//            this.value = text;
//        }
//
//        @Override
//        public String toString() {
//            return value;
//        }
//    }

    // Dispositivo
    public Dispositivo(){
        this.nombre = "";
        this.consumoHora = 0;
        this.estado = new Context("activo");
    }
    public Dispositivo(String nombre, double consumoHora, String estado) {
        this.nombre = nombre;
        this.consumoHora = consumoHora;
        this.estado = new Context(estado);
    }

    // Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Consumo por Hora
    public double getConsumoHora() {
        return consumoHora;
    }
    public void setConsumoHora(int consumoHora) {
        this.consumoHora = consumoHora;
    }

    // Estado del dispositivo.
    public Context getEstado() {
        return estado;

    }

    @Override
    public String toString() {
        return  "Dispositivo: \n"+
                "\tNombre: " + getNombre() + "\n" +
                "\tConsumo por Hora: " + getConsumoHora() + "\n" +
                "\tEstado: " + getEstado() + "\n";
    }

    public int getUso_minimo() { return 0; }
    public int getUso_maximo() { return 0;}

}

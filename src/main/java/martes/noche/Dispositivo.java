package martes.noche;

//TODO: ver porque no importa el gson
import org.json.JSONObject;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.io.Reader;

public class Dispositivo {

    private String nombre;
    private int consumoHora;
    private String estado;

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
        this.estado = estado;
    }

    @Override
    public String toString() {
        return  "Dispositivo: \n"+
                "\tNombre: " + getNombre() + "\n" +
                "\tConsumo por Hora: " + getConsumoHora() + "\n" +
                "\tEstado: " + getEstado() + "\n";
    }
}

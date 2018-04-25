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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getConsumoHora() {
        return consumoHora;
    }

    public void setConsumoHora(int consumoHora) {
        this.consumoHora = consumoHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}

package martes.noche;

//TODO: ver porque no importa el gson
//import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Dispositivo {

    private String nombre;
    private int consumoHora;
    private String estado;

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

//TODO: descomentar codigo cuando resuelva el primer problema y ver si funciona
/*    public static void leerJSON() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader("D:\\staff.json")) {

            // Convert JSON to Java Object
            Dispositivo dispositivo = gson.fromJson(reader, dispositivo.class);
            System.out.println(dispositivo);

            // Ejemplo de JSON a importar
            *//*
              "dispositivo": [
                 {
                  "nombre": "Play Station 5",
                  "consumoHora": 5000,
                  "estado": "activo",
                 }]
            *//*

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

}

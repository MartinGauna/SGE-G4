package ar.edu.utn.frba.dds.jsonParser;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class JsonParserTest {

    @Test
    public void loadDispositivosInteligentesJSON() throws IOException {
       JsonParser jp = new JsonParser();
       List<DispositivoInteligente> dispositivos = jp.loadDispositivosInteligentesJSON("/listaDispositivosInteligentes.json");
       for(Dispositivo disp : dispositivos)
       {
           System.out.println("-------");
           System.out.println("Nombre: " + disp.getNombre());
           System.out.println("Estado: " + disp.getEstado().getEstado());
           System.out.println("Consumo: " + disp.getConsumoHora());
           System.out.println("Uso Max: " + disp.getUso_maximo());
           System.out.println("Uso Min: " + disp.getUso_minimo());
           System.out.println("-------");
       }
    }
}
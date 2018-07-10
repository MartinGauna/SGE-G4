package ar.edu.utn.frba.dds.jsonParser;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class JsonParserTest {

    @Before
    public void setUp() throws Exception {


    }
    @Test
    public void loadDispositivosInteligentesJSON() throws IOException {
       JsonParser jp = new JsonParser();
       List<DispositivoInteligente> dispositivos = jp.loadDispositivosInteligentesJSON("/listaDispositivosInteligentes.json");

       System.out.println("DISPOSITIVOS");
       for(DispositivoInteligente disp : dispositivos)
       {
           System.out.println("-------");
           System.out.println("Nombre: " + disp.getNombre());
           //System.out.println("Estado: " + disp.getEstado().getEstado());
           System.out.println("Consumo: " + disp.getConsumoHora());
           System.out.println("Uso Max: " + disp.getUso_maximo());
           System.out.println("Uso Min: " + disp.getUso_minimo());
           System.out.println("-------");
       }
    }
}
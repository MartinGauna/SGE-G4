package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.jsonParser.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ZonaTest {


    @Before
    public void setUp() {
    }

    @Test
    public void parseJSON() throws IOException {
        List<Zona> zonas;
        JsonParser parser = new JsonParser();
        zonas = parser.loadZonaJSON();
        for (Zona zona : zonas) {
            System.out.println("-------");
            System.out.println("Zona: " + zona.getNombreDescriptivo());
            System.out.println("Latitud: " + zona.getLatitud());
            System.out.println("Longitud: " + zona.getLongitud());
            System.out.println("Radio: " + zona.getMetrosRedonda());
            System.out.println("-------");
        }
    }
}

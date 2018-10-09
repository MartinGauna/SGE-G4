package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteTV;
import ar.edu.utn.frba.dds.dispositivo.InstanciasDispositivo.DispositivoInteligenteTV24;
import ar.edu.utn.frba.dds.jsonParser.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransformadorTest {

    private Zona almagro;
    private Zona sanTelmo;

    @Before
    public void setUp() {
        Date f = new Date(2018, 12, 12);
        //CASO 1
        Categoria cat1 = new Categoria("1");
        Cliente client1 = new Cliente("Matias", "Vivone", "Cossio 6005", "matiasv@wings4u.com", "123", LocalDate.now(), "DNI", 36728917, 112305, cat1, LocalDate.now(), true);
        List<Cliente> clientes1 = new ArrayList<Cliente>();
        DispositivoInteligenteTV tv1 = new DispositivoInteligenteTV24("HD", "activo");
        tv1.addConsumo(123, f, f);
        client1.addDispositivo(tv1);
        clientes1.add(client1);
    }
    @Test
    public void parseJSON() throws IOException {
        List<Transformador> trafos;
        JsonParser parser = new JsonParser();
        trafos = parser.loadTransformadorJSON();
        for (Transformador t : trafos) {
            System.out.println("-------");
            System.out.println("latitud: " + t.getLatitud());
            System.out.println("Longitud: " + t.getLongitud());
            System.out.println("zona: " + t.getZona());
            System.out.println("-------");
        }
    }
}
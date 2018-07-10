package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteTV;
import ar.edu.utn.frba.dds.jsonParser.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class ZonaTest {
private Zona almagro;
private Zona sanTelmo;

    @Before
    public void setUp() {
        Date f = new Date(2018,12,12);
        //CASO 1
        Categoria cat1 = new Categoria("1");
        Cliente client1 = new Cliente("Matias","Vivone", "Cossio 6005","matiasv@wings4u.com","123",LocalDate.now(),"DNI",36728917,112305,cat1, LocalDate.now());
        List<Cliente> clientes1 = new ArrayList<Cliente>();
        DispositivoInteligenteTV tv1 = new DispositivoInteligenteTV("HD",0.123,"activo");
        tv1.addConsumo(123, f,f);
        client1.addDispositivo(tv1);
        clientes1.add(client1);
        Transformador transformador1 = new Transformador(clientes1);
        List<Transformador> transformadores1 = new ArrayList<Transformador>();
        transformadores1.add(transformador1);
        almagro = new Zona("Almagro",-34.618680,-58.415423,transformadores1,300);


        //CASO 2
        Categoria cat2 = new Categoria("1");
        Cliente client2 = new Cliente("Nicolas","Mena", "Cossio 6005","matiasvivone@hotmail.com","123",LocalDate.now(),"DNI",36728917,112305,cat2, LocalDate.now());

        List<Cliente> clientes2 = new ArrayList<Cliente>();

        DispositivoInteligenteTV tv2 = new DispositivoInteligenteTV("HD",0.123,"activo");
        tv2.addConsumo(321,f,f);
        client2.addDispositivo(tv2);
        clientes2.add(client2);
        Transformador transformador2 = new Transformador(clientes2);
        List<Transformador> transformadores2 = new ArrayList<Transformador>();
        transformadores2.add(transformador2);
        sanTelmo = new Zona("San Telmo",-34.6210356,-58.373654,transformadores2,300);

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

    @Test
    public void getConsumoTotal() throws IOException {
        System.out.println("------------------------");
        System.out.println("Consumo Total de Almagro");
        System.out.println(almagro.getConsumoTotal());
        System.out.println("------------------------");
        System.out.println("Consumo Total de San Telmo");
        System.out.println(sanTelmo.getConsumoTotal());

    }
}

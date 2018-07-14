package ar.edu.utn.frba.dds;


import ar.edu.utn.frba.dds.dispositivo.DIFactoryMethod;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import ar.edu.utn.frba.dds.helpers.AdapterSimplex;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ClienteTest {

    private Cliente client;
    private DIFactoryMethod fm;
    private DIFactoryMethod fm2;
    private DIFactoryMethod fm3;
    private DispositivoInteligente d1;
    private DispositivoInteligente d2;
    private DispositivoInteligente d3;
    private List<DispositivoInteligente> dispositivoInteligenteList = new ArrayList<>() ;
    private AdapterSimplex adapterSimplex = new AdapterSimplex();


    @Before
    public void setUp() throws Exception {
        this.client = new Cliente("CliName", "CliApell", "CliDom", "CliUser",
                "CliPass", LocalDate.of(2018,04, 28),"DNI",
                12345678, 45555533, null , LocalDate.now(),true);
        /**
         - x0 -> Es un televisor LCD de 40” cuyo consumo kWh es 0.18
         - x1 -> Es un lavarropas Automático de 5 kg con calentamiento de agua cuyo consumo kwH es 0.875
         - x2 -> Es un ventilador de techo cuyo consumo kWh es 0.06
         **/
        Date f = new Date(2018,12,12);

        Categoria cat1 = new Categoria("1");
        fm = new DIFactoryMethod();
        fm2 = new DIFactoryMethod();
        fm3 = new DIFactoryMethod();
        d1 = fm.crearDispositivo("Televisor 40", 0.18, "activo",false);
        d2 = fm2.crearDispositivo("Lavarropas 5kg", 0.875, "activo",false);
        d3 = fm3.crearDispositivo("Ventilador Techo", 0.06, "activo",false);

        d1.addConsumo(123, f,f);
        d1.addConsumo(150, f,f);
        d1.addConsumo(50, f,f);
        d1.addConsumo(80, f,f);

        dispositivoInteligenteList.add(d1);
        dispositivoInteligenteList.add(d2);
        dispositivoInteligenteList.add(d3);

        client.addDispositivo(d1);
        client.addDispositivo(d2);
        client.addDispositivo(d3);
    }

    @Test
    public void addDispInteligenteTest() {
        fm = new DIFactoryMethod();
        DispositivoInteligente heladera  = fm.crearDispositivo("Heladera", 20, "activo",false);

        assertEquals(this.client.getPuntaje(), 0);
        this.client.addDispositivo(heladera);
        assertEquals(this.client.getPuntaje(), 15);
    }

    @Test
    public void addDispEstandardTest() {
        fm = new DIFactoryMethod();
        Estandard heladera = new Estandard("Heladera", 20, "activo");

        assertEquals(this.client.getPuntaje(), 0);
        this.client.addDispositivo(heladera);
        assertEquals(this.client.getPuntaje(), 10);
    }
    @Test
    public void ahorroAutomatico() {
        adapterSimplex.reporteConsumoEficiente(dispositivoInteligenteList);
        System.out.println("---------");
        System.out.println("Value: " + adapterSimplex.solucion.getValue());
        for(int i = 0;i < adapterSimplex.solucion.getPoint().length;i++)
        {
            System.out.println("Variable Dispositivo " + i + ": " + adapterSimplex.solucion.getPoint()[i]);
            System.out.println("Dispositivo Consumo UTD" + i + ": " + dispositivoInteligenteList.get(i).getConsumoTotal());
            System.out.println("Estado Dispositivo" + i + ": " + dispositivoInteligenteList.get(i).getEstado().getEstado());
        }

        System.out.println("---------");

        client.ahorroAutomatico();

        for(int i = 0;i < adapterSimplex.solucion.getPoint().length;i++)
        {
            System.out.println("Variable Dispositivo " + i + ": " + adapterSimplex.solucion.getPoint()[i]);
            System.out.println("Dispositivo Consumo UTD" + i + ": " + dispositivoInteligenteList.get(i).getConsumoTotal());
            System.out.println("Estado Dispositivo" + i + ": " + dispositivoInteligenteList.get(i).getEstado().getEstado());
        }
    }
}

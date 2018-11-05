package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dispositivo.DIFactory;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AdapterSimplexTest {
private Cliente client;
private List<DispositivoInteligente> dispositivoInteligenteList = new ArrayList<>() ;
private AdapterSimplex adapterSimplex = new AdapterSimplex();
private DIFactory fm;
private DIFactory fm2;
private DIFactory fm3;
private DispositivoInteligente d1;
private DispositivoInteligente d2;
private DispositivoInteligente d3;

    @Before
    public void setUp() throws Exception {
         /**
         - x0 -> Es un televisor LCD de 40” cuyo consumo kWh es 0.18
         - x1 -> Es un lavarropas Automático de 5 kg con calentamiento de agua cuyo consumo kwH es 0.875
         - x2 -> Es un ventilador de techo cuyo consumo kWh es 0.06
         **/

        fm = new DIFactory();
        fm2 = new DIFactory();
        fm3 = new DIFactory();
        d1 = fm.crearDispositivo("Televisor 40", 0.18, "activo",false);
        d2 = fm2.crearDispositivo("Lavarropas 5kg", 0.875, "activo",false);
        d3 = fm3.crearDispositivo("Ventilador Techo", 0.06, "activo",false);

        dispositivoInteligenteList.add(d1);
        dispositivoInteligenteList.add(d2);
        dispositivoInteligenteList.add(d3);
    }

    @Test
    public void reporteConsumoEficienteTest()
    {
        adapterSimplex.reporteConsumoEficiente(dispositivoInteligenteList);
        System.out.println("---------");
        System.out.println("Value: " + adapterSimplex.solucion.getValue());
        for(int i = 0;i < adapterSimplex.solucion.getPoint().length;i++)
        {
            System.out.println("Variable Dispositivo " + i + ": " + adapterSimplex.solucion.getPoint()[i]);
        }

        System.out.println("---------");
    }
}
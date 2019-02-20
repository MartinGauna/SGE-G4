package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.Categoria;
import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dispositivo.DIFactory;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BackgroundProcessesTest {
    private DIFactory fm;
    private DIFactory fm2;
    private DIFactory fm3;
    private DispositivoInteligente d1;
    private DispositivoInteligente d2;
    private DispositivoInteligente d3;
    private List<DispositivoInteligente> dispositivoInteligenteList = new ArrayList<>() ;
    private AdapterSimplex adapterSimplex = new AdapterSimplex();
    private Cliente client1;
    private BackgroundProcesses bkg = BackgroundProcesses.getInstance();
    @Before
    public void setUp() throws Exception {
        /**
         - x0 -> Es un televisor LCD de 40” cuyo consumo kWh es 0.18
         - x1 -> Es un lavarropas Automático de 5 kg con calentamiento de agua cuyo consumo kwH es 0.875
         - x2 -> Es un ventilador de techo cuyo consumo kWh es 0.06
         **/
        Date f = new Date(2018,12,12);

        Categoria cat1 = new Categoria("1");
        client1 = new Cliente("Matias","Vivone", "Cossio 6005","matiasv@wings4u.com","123",LocalDate.now(),"DNI",36728917,112305,cat1, LocalDate.now(),true,0);
        fm = new DIFactory();
        fm2 = new DIFactory();
        fm3 = new DIFactory();
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

        client1.addDispositivo(d1);
        client1.addDispositivo(d2);
        client1.addDispositivo(d3);

    }



    @Test
    public void automatizacionAhorroAutomatico() throws InterruptedException {
     //bkg.automatizacionAhorroAutomatico(client1,1000*5);
     Thread.sleep(20000);
    }

    }
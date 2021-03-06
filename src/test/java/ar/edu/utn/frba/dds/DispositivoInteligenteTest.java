package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteHeladera;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DispositivoInteligenteTest {
    public DispositivoInteligente dispInt;

    @Before
    public void setUp() throws Exception {
        this.dispInt = new DispositivoInteligenteHeladera("Heladera",123,"activo",false);
    }

    @Test
    public void addConsumoTest() throws ParseException {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date d = sdf.parse("12/12/2018");
     Date e = sdf.parse("18/12/2018");
     this.dispInt.addConsumo(1200, d , e);

     Date f = sdf.parse("19/12/2018");
     Date g = sdf.parse("21/12/2018");
     this.dispInt.addConsumo(2100, f , g);
    }

    @Test
    public void getConsumoTotalTest() throws ParseException {

        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

        Date d = sdf2.parse("12/12/2018");
        Date e = sdf2.parse("18/12/2018");
        this.dispInt.addConsumo(1200, d , e);

        Date f = sdf2.parse("19/12/2018");
        Date g = sdf2.parse("21/12/2018");
        this.dispInt.addConsumo(2100, f , g);

        Date h = sdf2.parse("11/12/2018");
        Date i = sdf2.parse("22/12/2018");

        assertEquals(3300, this.dispInt.getConsumoTotal(h,i));
    }
}

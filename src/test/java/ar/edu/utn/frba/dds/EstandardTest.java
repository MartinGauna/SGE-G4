package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dispositivo.Adaptador;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class EstandardTest {

    private Estandard tvEstandard;
    private Adaptador adaptadorTV;

    @Before
    public void setUp() throws Exception {

        this.tvEstandard = new Estandard("Heladera", 10, "activo");
        this.adaptadorTV = new Adaptador(this.tvEstandard);
    }


    @Test
    public void getConsumoHoraTest() throws Exception{
        assertEquals(10, tvEstandard.getConsumoHora());
    }

    @Test
    public void getConsumoTotalTest() throws ParseException {

        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

        Date d = sdf2.parse("12/12/2018");
        Date e = sdf2.parse("18/12/2018");
        this.adaptadorTV.addConsumo(1200, d , e);

        Date f = sdf2.parse("19/12/2018");
        Date g = sdf2.parse("21/12/2018");
        this.adaptadorTV.addConsumo(2100, f , g);

        Date h = sdf2.parse("11/12/2018");
        Date i = sdf2.parse("22/12/2018");

        assertEquals(3300, this.tvEstandard.getAdaptador().getConsumoTotal(h,i));
        //System.out.println(this.tvEstandard.getAdaptador().getConsumoTotal(h,i));
    }


}
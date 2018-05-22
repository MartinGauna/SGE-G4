package martes.noche;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EstandardTest {

    private Estandard tvEstandard;
    private Adaptador adaptadorTV;

    @Before
    public void setUp() throws Exception {
        this.tvEstandard = new Estandard("Heladera", 10, "Activo");
    }

    @Test
    public void getConsumoHoraTest() throws Exception{
        assertEquals(10, tvEstandard.getConsumoHora());
    }


}
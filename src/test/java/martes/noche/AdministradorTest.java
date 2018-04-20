package martes.noche;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.Period;

public class AdministradorTest {

    private Administrador admin;

    @Before
    public void setUp() throws Exception {
        this.admin = new Administrador(LocalDate.of(2018,01, 01), 1234567);
    }

    @Test
    public void getfechaAltaTest() throws Exception{
        assertEquals(LocalDate.of(2018,01, 01), admin.getFechaAlta());
    }

    @Test
    public void getLegajoTest() throws Exception{
        assertEquals(1234567, admin.getLegajo());
    }

    @Test
    public void getAntiguedadTest() throws Exception{
        long diff = Period.between(admin.getFechaAlta(), LocalDate.now()).toTotalMonths();
        assertEquals(diff, admin.getAntiguedad());
    }
}

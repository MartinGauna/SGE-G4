package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.Administrador;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.Period;

public class AdministradorTest {

    private Administrador admin;

    @Before
    public void setUp() throws Exception {
        this.admin = new Administrador("AdminNanme","AdminApell", "ADomi", "AdminUser",
                "AdminPAss", LocalDate.of(2018,01, 01), 1234567);
    }

    @Test
    public void getAntiguedadTest() throws Exception{
        long diff = Period.between(admin.getFechaAlta(), LocalDate.now()).toTotalMonths();
        assertEquals(diff, admin.getAntiguedad());
    }
}

package martes.noche;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;


public class ClienteTest {

    private Cliente testClient;

    @Before
    public void setUp() throws Exception {
        this.testClient = new Cliente("DNI", 12345678, 45555533, null , LocalDate.now());

    }

    @Test
    public void getTipoDocTest() throws Exception{
        assertEquals("DNI",testClient.getTipoDoc());
    }

    @Test
    public void getNumeroDocTest() throws Exception{
        assertEquals(12345678,testClient.getNumeroDoc());
    }

    @Test
    public void getTelefonoTest() throws Exception{
        assertEquals(45555533,(long) testClient.getTelefono());
    }

    @Test
    public void getDateTest() throws Exception{
        assertEquals(LocalDate.now(),testClient.getFechaAlta());
    }
}

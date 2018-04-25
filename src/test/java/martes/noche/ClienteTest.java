package martes.noche;


import martes.noche.categoria.R1;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;


public class ClienteTest {

    private Cliente testClient;
    private R1 r1;

    @Before
    public void setUp() throws Exception {
        this.r1 = new R1();
        this.testClient = new Cliente("DNI", 12345678, 45555533, r1 , LocalDate.now());

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
    public void getCategoriaTest() throws Exception{
        assertEquals(r1,testClient.getCategoria());
    }

    @Test
    public void getDateTest() throws Exception{
        assertEquals(LocalDate.now(),testClient.getFechaAltaServicio());
    }
}

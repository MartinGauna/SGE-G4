package martes.noche;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DispositivoTest {

    private Dispositivo device;

    @Before
    public void setUp() throws Exception {
        this.device = new Dispositivo("Condensador de flujo", 80000, "activo");
    }

    @Test
    public void getNombreTest() throws Exception{
        assertEquals("Condensador de flujo", device.getNombre());
    }

    @Test
    public void getConsumoTest() throws Exception{
        assertEquals(80000, device.getConsumoHora());
    }
    @Test
    public void getEstadoTest() throws Exception{
        assertEquals("activo", device.getEstado());
    }
}

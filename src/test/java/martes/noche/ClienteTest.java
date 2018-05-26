package martes.noche;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Objects;


public class ClienteTest {

    private Cliente client;
    private DIFactoryMethod fm;

    @Before
    public void setUp() throws Exception {
        this.client = new Cliente("CliName", "CliApell", "CliDom", "CliUser",
                "CliPass", LocalDate.of(2018,04, 28),"DNI",
                12345678, 45555533, null , LocalDate.now());
    }

    @Test
    public void addDispInteligenteTest() {
        fm = new DIFactoryMethod();
        DispositivoInteligente heladera  = fm.crearDispositivo("Heladera", 20, "activo");

        assertEquals(this.client.getPuntaje(), 0);
        this.client.addDispositivo(heladera);
        assertEquals(this.client.getPuntaje(), 15);
    }

    @Test
    public void addDispEstandardTest() {
        fm = new DIFactoryMethod();
        Estandard heladera = new Estandard("Heladera", 20, "activo");

        assertEquals(this.client.getPuntaje(), 0);
        this.client.addDispositivo(heladera);
        assertEquals(this.client.getPuntaje(), 10);
    }
}

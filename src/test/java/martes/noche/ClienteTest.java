package martes.noche;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;


public class ClienteTest {

    private Cliente testClient;

    @Before
    public void setUp() throws Exception {
        this.testClient = new Cliente("CliName", "CliApell", "CliDom", "CliUser",
                "CliPass", LocalDate.of(2018,04, 28),"DNI",
                12345678, 45555533, null , LocalDate.now());
    }


}

package martes.noche.categoria;

import martes.noche.categoria.R1;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class R1Test {

    private R1 r1;

    @Before
    public void setUp() throws Exception {
        r1 = new R1(18.76, 0.644);
    }

    @Test
    public void getCargoFijoTest() throws Exception{
        assertTrue(18.76 == r1.getCargoFijo());
    }

    @Test
    public void getCargoVariableTest() throws Exception{
        assertTrue(0.644 == r1.getCargoVariable());
    }

    @Test
    public void CalculoAproximadoTest() throws Exception{
        assertTrue(20.692 == r1.calcularValorAprox(3));
    }
}

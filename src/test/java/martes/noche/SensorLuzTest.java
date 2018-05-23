package martes.noche;

import martes.noche.sensor.Sensor;
import martes.noche.sensor.SensorLuz;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SensorLuzTest {
    private SensorLuz sensor;
    @Before
    public void setUp() {
        sensor = new SensorLuz(1);
    }

    @Test
    public void obtenermedicion(){

        System.out.println(sensor.getMedicion());
        long prev = sensor.getMedicion().getValor();
        for(int i =0; i<100000 ;i++){
            if(prev != sensor.getMedicion().getValor()){
                prev = sensor.getMedicion().getValor();
                System.out.println(prev);
            }
        }
    }
}

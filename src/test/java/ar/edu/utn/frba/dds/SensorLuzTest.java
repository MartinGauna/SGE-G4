package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.sensor.SensorLuz;
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
        long prev = sensor.getMedicion().getValor();
        for(int i =0; i<100000 ;i++){
            if(prev != sensor.getMedicion().getValor()){
                prev = sensor.getMedicion().getValor();
                System.out.println(prev);
            }
        }
    }
}

package ar.edu.utn.frba.dds.regla;

import ar.edu.utn.frba.dds.actuador.ActuadorAAcondicionado;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;
import ar.edu.utn.frba.dds.sensor.SensorHumedad;
import ar.edu.utn.frba.dds.sensor.SensorLuz;
import ar.edu.utn.frba.dds.sensor.SensorTemperatura;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ReglaTest {
    private DispositivoInteligenteAAcondicionado aire;
    private ActuadorAAcondicionado actuadorAire;
    private SensorTemperatura sensorTemperatura;
    private SensorHumedad sensorHumedad;
    private ArrayList<Condicion> condiciones;
    private SensorLuz sensorLuz;
    private long harcodedTemperatura;
    private long harcodedHumedad;
    private long temperatura;
    private long humedad;


    @Before
    public void setUp() throws Exception {
       this.aire = new DispositivoInteligenteAAcondicionado("Aire Comedor",1200,"apagado",false);
       this.actuadorAire = new ActuadorAAcondicionado(aire);
       this.sensorTemperatura  = new SensorTemperatura(1);
       this.sensorHumedad = new SensorHumedad(1);
       this.condiciones =  new ArrayList<Condicion>();
       this.temperatura =  sensorTemperatura.getMedicion().getValor();
       this.humedad = sensorHumedad.getMedicion().getValor();


        /** TEST DESCRIPTION: Encender Aire Acondicionario SII:
         * a) Temperatura > 23° y Humedad > 90%
         * OR
         * b) Temperatura >28°
         **/

    }

    @Test
    public void print_console_header() {

        System.out.println("********************** ");
        System.out.println("******* BATERIA DE TESTS UNITARIOS PARA REGLAS");
        System.out.println("********************** ");

        System.out.println("Las condiciones del test son: Temperatura > 23° & Humedad > 60% || Temperatura > 28° ");
        System.out.println("La temperatura real es de: " + temperatura + "°");
        System.out.println("La humedad real es de: " + humedad + "°");
        System.out.println("Se usarán valores <<ficticios>> de Temperatura y Humedad para cubrir todos los escenarios");

    }


    @Test
    public void update_test_1() {
        System.out.println("********************** ");
        System.out.println("******* TEST 1 ******* ");
        System.out.println("********************** ");

        harcodedTemperatura = 24;
        System.out.println("La temperatura muestraria para este test es de: " + harcodedTemperatura + "°");

        System.out.println("-------");

        harcodedHumedad = 80;
        System.out.println("La humedad muestraria para este test es de: " + harcodedHumedad + "%");

        System.out.println("-------");

        System.out.println("Estado inicial del dispositivo:  " + aire.getEstado().getEstado());

        //CONDICION 1 --> TEMPERATURA 23°
        Condicion cond1 = new Condicion('>',harcodedTemperatura,23);

        //CONDICION 2 --> HUMEDAD > 9
        Condicion cond2 = new Condicion('>',harcodedHumedad,90,'&');

        //CONDICION 3 --> TEMPERATURA 23°
        Condicion cond3 = new Condicion('>',harcodedTemperatura,28,'|');


        condiciones.add(cond1);
        condiciones.add(cond2);
        condiciones.add(cond3);

        Regla regla_test_1 = new Regla(actuadorAire,"prenderDispositivo",condiciones);
        System.out.println("Procesando regla...");
        regla_test_1.ejecutar();

        System.out.println("Estado final del dispositivo: " + aire.getEstado().getEstado());

        System.out.println("-------");

        System.out.println("******* FIN TEST 1 ******* ");
    }

    @Test
    public void update_test_2() {
        System.out.println("********************** ");
        System.out.println("******* TEST 2 ******* ");
        System.out.println("********************** ");

        harcodedTemperatura = 24;
        System.out.println("La temperatura muestraria para este test es de: " + harcodedTemperatura + "°");

        System.out.println("-------");

        harcodedHumedad = 95;
        System.out.println("La humedad muestraria para este test es de: " + harcodedHumedad + "%");

        System.out.println("-------");

        System.out.println("Estado inicial del dispositivo:  " + aire.getEstado().getEstado());

        //CONDICION 1 --> TEMPERATURA 23°
        Condicion cond1 = new Condicion('>',harcodedTemperatura,23);

        //CONDICION 2 --> HUMEDAD > 9
        Condicion cond2 = new Condicion('>',harcodedHumedad,90,'&');

        //CONDICION 3 --> TEMPERATURA 23°
        Condicion cond3 = new Condicion('>',harcodedTemperatura,28,'|');


        condiciones.add(cond1);
        condiciones.add(cond2);
        condiciones.add(cond3);

        Regla regla_test_2 = new Regla(actuadorAire,"prenderDispositivo",condiciones);
        System.out.println("Procesando regla...");
        regla_test_2.ejecutar();

        System.out.println("Estado final del dispositivo: " + aire.getEstado().getEstado());

        System.out.println("-------");

        System.out.println("******* FIN TEST 2 ******* ");
    }

    @Test
    public void update_test_3() {
        System.out.println("********************** ");
        System.out.println("******* TEST 3 ******* ");
        System.out.println("********************** ");

        harcodedTemperatura = 20;
        System.out.println("La temperatura muestraria para este test es de: " + harcodedTemperatura + "°");

        System.out.println("-------");

        harcodedHumedad = 95;
        System.out.println("La humedad muestraria para este test es de: " + harcodedHumedad + "%");

        System.out.println("-------");

        System.out.println("Estado inicial del dispositivo:  " + aire.getEstado().getEstado());

        //CONDICION 1 --> TEMPERATURA 23°
        Condicion cond1 = new Condicion('>',harcodedTemperatura,23);

        //CONDICION 2 --> HUMEDAD > 9
        Condicion cond2 = new Condicion('>',harcodedHumedad,90,'&');

        //CONDICION 3 --> TEMPERATURA 23°
        Condicion cond3 = new Condicion('>',harcodedTemperatura,28,'|');


        condiciones.add(cond1);
        condiciones.add(cond2);
        condiciones.add(cond3);

        Regla regla_test_3 = new Regla(actuadorAire,"prenderDispositivo",condiciones);
        System.out.println("Procesando regla...");
        regla_test_3.ejecutar();

        System.out.println("Estado final del dispositivo: " + aire.getEstado().getEstado());

        System.out.println("-------");

        System.out.println("******* FIN TEST 3 ******* ");
    }

    @Test
    public void update_test_4() {
        System.out.println("********************** ");
        System.out.println("******* TEST 4 ******* ");
        System.out.println("********************** ");

        harcodedTemperatura = 29;
        System.out.println("La temperatura muestraria para este test es de: " + harcodedTemperatura + "°");

        System.out.println("-------");

        harcodedHumedad = 50;
        System.out.println("La humedad muestraria para este test es de: " + harcodedHumedad + "%");

        System.out.println("-------");

        System.out.println("Estado inicial del dispositivo:  " + aire.getEstado().getEstado());

        //CONDICION 1 --> TEMPERATURA 23°
        Condicion cond1 = new Condicion('>',harcodedTemperatura,23);

        //CONDICION 2 --> HUMEDAD > 9
        Condicion cond2 = new Condicion('>',harcodedHumedad,90,'&');

        //CONDICION 3 --> TEMPERATURA 23°
        Condicion cond3 = new Condicion('>',harcodedTemperatura,28,'|');


        condiciones.add(cond1);
        condiciones.add(cond2);
        condiciones.add(cond3);

        Regla regla_test_4 = new Regla(actuadorAire,"prenderDispositivo",condiciones);
        System.out.println("Procesando regla...");
        regla_test_4.ejecutar();

        System.out.println("Estado final del dispositivo: " + aire.getEstado().getEstado());

        System.out.println("-------");

        System.out.println("******* FIN TEST 4 ******* ");
    }

    @Test
    public void update_test_5() {
        System.out.println("********************** ");
        System.out.println("******* TEST 5 ******* ");
        System.out.println("********************** ");

        harcodedTemperatura = 29;
        System.out.println("La temperatura muestraria para este test es de: " + harcodedTemperatura + "°");

        System.out.println("-------");

        harcodedHumedad = 99;
        System.out.println("La humedad muestraria para este test es de: " + harcodedHumedad + "%");

        System.out.println("-------");

        System.out.println("Estado inicial del dispositivo:  " + aire.getEstado().getEstado());

        //CONDICION 1 --> TEMPERATURA 23°
        Condicion cond1 = new Condicion('>',harcodedTemperatura,23);

        //CONDICION 2 --> HUMEDAD > 9
        Condicion cond2 = new Condicion('>',harcodedHumedad,90,'&');

        //CONDICION 3 --> TEMPERATURA 23°
        Condicion cond3 = new Condicion('>',harcodedTemperatura,28,'|');


        condiciones.add(cond1);
        condiciones.add(cond2);
        condiciones.add(cond3);

        Regla regla_test_5 = new Regla(actuadorAire,"prenderDispositivo",condiciones);
        System.out.println("Procesando regla...");
        regla_test_5.ejecutar();

        System.out.println("Estado final del dispositivo: " + aire.getEstado().getEstado());

        System.out.println("-------");

        System.out.println("******* FIN TEST 5 ******* ");
    }

    @Test
    public void update_test_6() {
        System.out.println("********************** ");
        System.out.println("******* TEST 6 ******* ");
        System.out.println("********************** ");

        harcodedTemperatura = 20;
        System.out.println("La temperatura muestraria para este test es de: " + harcodedTemperatura + "°");

        System.out.println("-------");

        harcodedHumedad = 50;
        System.out.println("La humedad muestraria para este test es de: " + harcodedHumedad + "%");

        System.out.println("-------");

        System.out.println("Estado inicial del dispositivo:  " + aire.getEstado().getEstado());

        //CONDICION 1 --> TEMPERATURA 23°
        Condicion cond1 = new Condicion('>',harcodedTemperatura,23);

        //CONDICION 2 --> HUMEDAD > 9
        Condicion cond2 = new Condicion('>',harcodedHumedad,90,'&');

        //CONDICION 3 --> TEMPERATURA 23°
        Condicion cond3 = new Condicion('>',harcodedTemperatura,28,'|');


        condiciones.add(cond1);
        condiciones.add(cond2);
        condiciones.add(cond3);

        Regla regla_test_6 = new Regla(actuadorAire,"prenderDispositivo",condiciones);
        System.out.println("Procesando regla...");
        regla_test_6.ejecutar();

        System.out.println("Estado final del dispositivo: " + aire.getEstado().getEstado());

        System.out.println("-------");

        System.out.println("******* FIN TEST 6 ******* ");
    }
}

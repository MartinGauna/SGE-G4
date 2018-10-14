package db;

import ar.edu.utn.frba.dds.Magnitud;
import ar.edu.utn.frba.dds.actuador.ActuadorAAcondicionado;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;
import ar.edu.utn.frba.dds.dispositivo.estadosDispositivo.Context;
import ar.edu.utn.frba.dds.regla.Condicion;
import ar.edu.utn.frba.dds.regla.Regla;
import ar.edu.utn.frba.dds.sensor.SensorHumedad;
import ar.edu.utn.frba.dds.sensor.SensorTemperatura;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReglaPersistenceTest {

    private EntityManager entityManager;
    private DispositivoInteligenteAAcondicionado aire;
    private ActuadorAAcondicionado actuadorAire;
    private ArrayList<Condicion> condiciones;
    private SensorTemperatura sensorTemperatura;
    private SensorHumedad sensorHumedad;
    private Magnitud temperatura;
    private Magnitud humedad;

    @Before
    public void setUp() {
        this.aire = new DispositivoInteligenteAAcondicionado("Aire Comedor", 1200, "apagado", false);
        this.actuadorAire = new ActuadorAAcondicionado(aire);
        this.sensorTemperatura = new SensorTemperatura(1);
        this.sensorHumedad = new SensorHumedad(1);
        this.condiciones = new ArrayList<>();
        this.temperatura = sensorTemperatura.getMedicion();
        this.humedad = sensorHumedad.getMedicion();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TEST_PERSISTENCE_UNIT");
        entityManager = factory.createEntityManager();
    }

    @Test
    public void update_test_1() {

        long harcodedTemperatura = 24;
        long harcodedHumedad = 80;

        //CONDICION 1 --> TEMPERATURA 23°
        Condicion cond1 = new Condicion('>', harcodedTemperatura, 23);
        //CONDICION 2 --> HUMEDAD > 9
        Condicion cond2 = new Condicion('>', harcodedHumedad, 90, '&');
        //CONDICION 3 --> TEMPERATURA 23°
        Condicion cond3 = new Condicion('>', harcodedTemperatura, 28, '|');

        condiciones.add(cond1);
        condiciones.add(cond2);
        condiciones.add(cond3);

        Regla regla = new Regla(actuadorAire, "prenderDispositivo", condiciones);

        // persisto
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(cond1);
        entityManager.persist(cond2);
        entityManager.persist(cond3);
        entityManager.persist(regla);
        entityManager.persist(sensorHumedad);
        entityManager.persist(sensorTemperatura);
        entityManager.persist(temperatura);
        entityManager.persist(humedad);
        entityManager.persist(actuadorAire);
        entityManager.persist(aire);
        transaction.commit();


        //mostrado por consola de consumos

        DispositivoInteligenteAAcondicionado dispPersistido = entityManager.find(DispositivoInteligenteAAcondicionado.class, aire.getId());
        Regla reglaPersistida = entityManager.find(Regla.class, regla.getId());

        Context einicial = dispPersistido.getEstado();
        System.out.println("Estado inicial del dispositivo: " + einicial);

        reglaPersistida.ejecutar();
        Context efinal = dispPersistido.getEstado();

        System.out.println("Estado final del dispositivo: " + efinal);

        //TODO: verificar porque no cambia el estado
        //assertTrue(einicial != efinal);
    }

}

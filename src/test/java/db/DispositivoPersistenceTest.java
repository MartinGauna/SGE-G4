package db;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Transformador;
import ar.edu.utn.frba.dds.Zona;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteHeladera;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class DispositivoPersistenceTest {


    private EntityManager entityManager;

    @Before
    public void before() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TEST_PERSISTENCE_UNIT");
        entityManager = factory.createEntityManager();
    }

    @Test
    public void testConnection() {
        // creo un nuevo cliente
        DispositivoInteligenteHeladera dispositivo = new DispositivoInteligenteHeladera("heladera", 15.5, "activo", true);

        // no esta persistido
        assertEquals(dispositivo.getId(), 0);

        // persisto en DB
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(dispositivo);
        transaction.commit();

        // id no es nulo
        assertNotNull(dispositivo.getId());

        // comparo identidad
        DispositivoInteligenteHeladera persisted = null;
        persisted = entityManager.find(DispositivoInteligenteHeladera.class, dispositivo.getId());
        assertEquals(dispositivo, persisted);

    }

    @Test
    public void cambioGeolocalizacionTest() {

        // creo un nuevo cliente
        Cliente cliente = new Cliente("CliName", "CliApell", "CliDom", "CliUser",
                "CliPass", LocalDate.of(2018, 04, 28), "DNI",
                87654321, 45555533, null, LocalDate.now(), true);

        Zona almagro = new Zona("Almagro", -34.618680, -58.415423, 300);
        Zona sanTelmo = new Zona("San Telmo", -34.6210356, -58.373654, 300);
        Transformador tra1 = new Transformador(-34.618680, -58.415423, almagro);
        Transformador tra2 = new Transformador(-34.618680, -58.415423, sanTelmo);
        almagro.addTransformador(tra1);
        sanTelmo.addTransformador(tra2);
        cliente.setTransformador(tra1);

        // persisto
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(almagro);
        entityManager.persist(sanTelmo);
        entityManager.persist(tra1);
        entityManager.persist(tra2);
        entityManager.persist(cliente);
        transaction.commit();

        //verifico que tiene asdignada la zona almagro

        Cliente clientePersistido = null;
        clientePersistido = entityManager.find(Cliente.class, cliente.getId());
        Zona almagroPersistida = null;
        almagroPersistida = entityManager.find(Zona.class, almagro.getId());
        Zona sanTelmoPersistida = null;
        sanTelmoPersistida = entityManager.find(Zona.class, sanTelmo.getId());

        assertEquals(clientePersistido.getTransformador().getZona(), almagroPersistida);
        assertNotEquals(clientePersistido.getTransformador().getZona(), sanTelmoPersistida);

        //hago el cambio de trasformador para que cambie de zona
        cliente.cambiarTransformador(tra2);

        //verifico el cambio de geolocalizacion
        assertNotEquals(clientePersistido.getTransformador().getZona(), almagroPersistida);
        assertEquals(clientePersistido.getTransformador().getZona(), sanTelmoPersistida);
    }
}


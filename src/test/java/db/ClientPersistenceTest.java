package db;

import ar.edu.utn.frba.dds.Categoria;
import ar.edu.utn.frba.dds.Cliente;

import static org.junit.Assert.*;

import ar.edu.utn.frba.dds.Transformador;
import ar.edu.utn.frba.dds.Zona;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class ClientPersistenceTest {

    private EntityManager entityManager;

    @Before
    public void before() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TEST_PERSISTENCE_UNIT");
        entityManager = factory.createEntityManager();
    }

    @Test
    public void testConnection() {
        // creo un nuevo cliente
        Cliente cliente = new Cliente("CliName", "CliApell", "CliDom", "CliUser",
                "CliPass", LocalDate.of(2018,04, 28),"DNI",
                12345678, 45555533, null , LocalDate.now(),true,0);

        // no esta persistido
        assertEquals(cliente.getId(), 0);

        // persisto en DB
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(cliente);
        transaction.commit();

        // id no es nulo
        assertNotNull(cliente.getId());

        // comparo identidad
        Cliente persisted = null;
        persisted = entityManager.find(Cliente.class, cliente.getId());
        assertEquals(cliente, persisted);

    }

    @Test
    public void cambioGeolocalizacionTest() {

        // creo un nuevo cliente
        Cliente cliente = new Cliente("CliName", "CliApell", "CliDom", "CliUser",
                "CliPass", LocalDate.of(2018,04, 28),"DNI",
                87654321, 45555533, null , LocalDate.now(),true,0);

        Zona almagro = new Zona("Almagro", -34.618680, -58.415423, 300);
        Zona sanTelmo = new Zona("San Telmo",-34.6210356,-58.373654,300);
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
package db;

import ar.edu.utn.frba.dds.Cliente;

import static org.junit.Assert.*;
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
                12345678, 45555533, null , LocalDate.now(),true);

        // id es null porque no esta persistido
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
}
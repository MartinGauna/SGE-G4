package db;

import ar.edu.utn.frba.dds.Administrador;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AdminPersistenceTest {

    private EntityManager entityManager;

    @Before
    public void before() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TEST_PERSISTENCE_UNIT");
        entityManager = factory.createEntityManager();
    }

    @Test
    public void testConnection() {
        // creo un nuevo cliente
        Administrador admin = new Administrador("AdminNanme", "AdminApell", "ADomi", "AdminUser",
                "AdminPAss", LocalDate.of(2018, 01, 01), 1234567);

        // no esta persistido
        assertEquals(admin.getId(), 0);

        // persisto en DB
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(admin);
        transaction.commit();

        // id no es nulo
        assertNotNull(admin.getId());

        // comparo identidad
        Administrador persisted = null;
        persisted = entityManager.find(Administrador.class, admin.getId());
        assertEquals(admin, persisted);
    }
}
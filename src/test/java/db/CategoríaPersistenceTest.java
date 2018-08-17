package db;

import ar.edu.utn.frba.dds.Categoria;
import ar.edu.utn.frba.dds.Cliente;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;


public class CategoríaPersistenceTest {

    private EntityManager entityManager;

    @Before
    public void before() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TEST_PERSISTENCE_UNIT");
        entityManager = factory.createEntityManager();
    }

    @Test
    public void testConnection() {

        // creo una nueva categoria
        Categoria cat = new Categoria("R7");
        //System.out.println(cat1.toString());

        // no esta persistido
        assertEquals(cat.getId(), 0);

        // persisto en DB
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(cat);
        transaction.commit();

        // id no es nulo
        assertNotNull(cat.getId());

        // comparo identidad
        Categoria persisted = null;
        persisted = entityManager.find(Categoria.class, cat.getId());
        assertEquals(cat, persisted);
    }

}

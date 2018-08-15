package db;

import org.junit.Assert;
import org.junit.Test;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import static org.junit.Assert.assertNotNull;
//import ar.edu.utn.frba.dds.accountManager.Indicator;


public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Test
    public void contextUp() {
        assertNotNull(entityManager());
    }
}

//	@Test
//	public void contextUpWithTransaction() throws Exception {
//		withTransaction(() -> {});
//	}
//
//	@Test
//	public void EntityTest() {
//
//		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
//		EntityTransaction tx = entityManager.getTransaction();
//
        //agregar tests para cada clase

//		tx.commit();
//		entityManager.clear();
//	}
//}
package db;

import ar.edu.utn.frba.dds.*;

import ar.edu.utn.frba.dds.jsonParser.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TransformadorPersistenceTest {

    private EntityManager entityManager;
    private JsonParser parser;

    @Before
    public void before() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TEST_PERSISTENCE_TRANSFORMADOR");
        entityManager = factory.createEntityManager();
        parser = new JsonParser();
    }

    private List<Transformador> loadTransformadorforTestJSON() throws IOException {

        Config config = new Config();
        Type TransformadorListType = new TypeToken<List<Transformador>>() {
        }.getType();
        List<Transformador> transformadores;
        try (Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream(config.getProperty("jsonTransformadoresTest")), StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().create();
            transformadores = gson.fromJson(reader, TransformadorListType);
        }
        return transformadores;
    }

    @Test
    public void countTransformadoresTest() throws IOException {

        List<Transformador> trafos = parser.loadTransformadorJSON();
        Zona sanTelmo = new Zona("San Telmo", -34.6210356, -58.373654, 300);
        EntityTransaction t1 = entityManager.getTransaction();
        EntityTransaction t2 = entityManager.getTransaction();
        EntityTransaction t3 = entityManager.getTransaction();

        t1.begin();
        entityManager.persist(sanTelmo);
        for (Transformador t : trafos) {
            t.setZona(sanTelmo);
            entityManager.persist(t);
        }
        t1.commit();

        int count = ((Long) entityManager.createQuery("select count(id) from Transformador ").getSingleResult()).intValue();
        assertTrue(count > 0);
        assertEquals(count, 3);

        //elimino todos los elementos de la tabla
        t2.begin();
        entityManager.createQuery("DELETE FROM Transformador ").executeUpdate();
        entityManager.flush();
        t2.commit();
        int empty = ((Long) entityManager.createQuery("select count(id) from Transformador ").getSingleResult()).intValue();
        assertEquals(0, empty);

        // agregado de transformador nuevo a json
        List<Transformador> trafosExendido = this.loadTransformadorforTestJSON();

        t3.begin();
        entityManager.flush();
        for (Transformador te : trafosExendido) {
            te.setZona(sanTelmo);
            entityManager.persist(te);
        }
        t3.commit();
        int newcount = ((Long) entityManager.createQuery("select count(id) from Transformador ").getSingleResult()).intValue();

        assertTrue(newcount > count);
        assertEquals(newcount, 4);
    }
}

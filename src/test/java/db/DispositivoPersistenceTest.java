package db;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.Transformador;
import ar.edu.utn.frba.dds.Zona;
import ar.edu.utn.frba.dds.actuador.ActuadorHeladera;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteHeladera;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class DispositivoPersistenceTest {


    private EntityManager entityManager;

    @Before
    public void before() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TEST_PERSISTENCE_GONZALO");
        entityManager = factory.createEntityManager();


    }

    @Test
    public void testConnection() {

        DispositivoInteligenteHeladera dispositivo = new DispositivoInteligenteHeladera("heladera", 15.5, "activo", true);
        ActuadorHeladera actuador = new ActuadorHeladera(dispositivo);

        // no esta persistido
        assertEquals(dispositivo.getId(), 0);

        // persisto en DB
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(dispositivo);
        entityManager.persist(actuador);
        transaction.commit();

        // id no es nulo
        assertNotNull(dispositivo.getId());

        // comparo identidad
        DispositivoInteligenteHeladera persisted = null;
        persisted = entityManager.find(DispositivoInteligenteHeladera.class, dispositivo.getId());
        assertEquals(dispositivo, persisted);

    }

    @Test
    public void periodosEncendidoTest() throws ParseException {

        // creo un nuevo cliente
        Cliente cliente = new Cliente("CliName", "CliApell", "CliDom", "CliUser",
                "CliPass", LocalDate.of(2018, 04, 28), "DNI",
                87654321, 45555533, null, LocalDate.now(), true);

        //creacion de dispositivo
        DispositivoInteligenteHeladera dispInt = new DispositivoInteligenteHeladera("Heladera", 123, "activo", false);
        cliente.addDispositivo(dispInt);

        ActuadorHeladera actuador = new ActuadorHeladera(dispInt);

        //consumos del dispositivo
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sdf.parse("12/12/2018");
        Date e = sdf.parse("18/12/2018");
        Consumo cons1 = new Consumo(dispInt, 1200, d, e);
        Date f = sdf.parse("19/12/2018");
        Date g = sdf.parse("21/12/2018");
        Consumo cons2 = new Consumo(dispInt, 2100, f, g);
        dispInt.addConsumo(cons1);
        dispInt.addConsumo(cons2);

        // persisto
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(cons1);
        entityManager.persist(cons2);
        entityManager.persist(dispInt);
        entityManager.persist(actuador);
        entityManager.persist(cliente);
        transaction.commit();

        //mostrado por consola de consumos

        Cliente clientePersistido = null;
        clientePersistido = entityManager.find(Cliente.class, cliente.getId());
        DispositivoInteligente dispPersistido = null;
        dispPersistido = entityManager.find(DispositivoInteligenteHeladera.class, dispInt.getId());
        Consumo cons1Persistido = null;
        cons1Persistido = entityManager.find(Consumo.class, cons1.getId());
        Consumo cons2Persistido = null;
        cons2Persistido = entityManager.find(Consumo.class, cons2.getId());

        assertEquals(clientePersistido.getNombre(), cliente.getNombre());
        assertEquals(dispPersistido.getNombre(), dispInt.getNombre());

        System.out.println("----------------------------------");
        System.out.println("Listado de intervalos de encendido");
        System.out.println("----------------------------------");

        for(Consumo c : dispPersistido.getConsumos()) {
            System.out.println("Consumo: " + c.getWatts());
            System.out.println("Inicio:  " + c.getFechaInicio());
            System.out.println("Fin:     " + c.getFechaFinal() + "\n");
        }

        //hago el cambio de nombre de dispositivo

        String nombrePrevio = dispInt.getNombre();
        String nombreNuevo = "Condensador de flujo";
        dispInt.setNombre(nombreNuevo);

        //verifico el cambio de nombre
        assertNotEquals(dispPersistido.getNombre(),nombrePrevio);
        assertEquals(dispPersistido.getNombre(),nombreNuevo);
    }
}


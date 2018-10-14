package db;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.GeneradorReportes;
import ar.edu.utn.frba.dds.actuador.ActuadorAAcondicionado;
import ar.edu.utn.frba.dds.actuador.ActuadorHeladera;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;
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

public class ConsumoPersistenceTest {

    private EntityManager entityManager;
    private GeneradorReportes reporte;
    private Cliente cliente1;
    private DispositivoInteligenteHeladera hel;
    private DispositivoInteligenteAAcondicionado aire;
    private ActuadorAAcondicionado actuadorAire;
    private ActuadorHeladera actuadorHel;
    private Consumo cons1;
    private Consumo cons2;
    private Consumo cons3;
    private Consumo cons4;
    private SimpleDateFormat sdf;

    @Before
    public void before() throws ParseException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TEST_PERSISTENCE_UNIT");
        entityManager = factory.createEntityManager();
        reporte = GeneradorReportes.getInstance();

        // creacion de clientes
        cliente1 = new Cliente("Pepe", "Ruiz", "Calle Falsa 123", "user1",
                "pass1", LocalDate.of(2018,04, 28),"DNI",
                87654321, 45555533, null , LocalDate.now(),true);

        //creacion de dispositivos
        hel = new DispositivoInteligenteHeladera("Heladera", 123, "activo", false);
        cliente1.addDispositivo(hel);
        aire = new DispositivoInteligenteAAcondicionado("Aire Comedor", 1200, "apagado", false);
        cliente1.addDispositivo(aire);
        actuadorAire = new ActuadorAAcondicionado(aire);
        actuadorHel = new ActuadorHeladera(hel);

        //genero consumos
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sdf.parse("12/12/2018");
        Date e = sdf.parse("18/12/2018");
        Date f = sdf.parse("19/12/2018");
        Date g = sdf.parse("21/12/2018");

        cons1 = new Consumo(hel, 1200, d, e);
        cons2 = new Consumo(hel, 2100, f, g);
        hel.addConsumo(cons1);
        hel.addConsumo(cons2);

        cons3 = new Consumo(hel, 3400, d, e);
        cons4 = new Consumo(hel, 4300, f, g);
        aire.addConsumo(cons3);
        aire.addConsumo(cons4);

        // persisto
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(cons1);
        entityManager.persist(cons2);
        entityManager.persist(cons3);
        entityManager.persist(cons4);
        entityManager.persist(actuadorAire);
        entityManager.persist(actuadorHel);
        entityManager.persist(hel);
        entityManager.persist(aire);
        entityManager.persist(cliente1);
        transaction.commit();

    }

    @Test
    public void consumoTotalPorHogarTest() throws ParseException {

        // 1 - Dado un hogar y un período, mostrar por consola el consumo total.

        Date inicio = sdf.parse("01/12/2018");
        Date fin = sdf.parse("29/12/2018");

        Cliente clientePersistido = entityManager.find(Cliente.class, cliente1.getId());

        String rep = reporte.generarReporteConsumoHogar(clientePersistido,inicio,fin);
        System.out.println(rep);
}

    @Test
    public void consumoPromedioPorDispositivoTest() throws ParseException {
        // 2- Dado un dispositivo y un período, mostrar por consola su consumo promedio.

        Date inicio = sdf.parse("01/12/2018");
        Date fin = sdf.parse("29/12/2018");

        DispositivoInteligenteAAcondicionado airePersistido = entityManager.find(DispositivoInteligenteAAcondicionado.class, aire.getId());

        int consumoTotal = airePersistido.getConsumoTotal(inicio, fin);
        int size = airePersistido.getConsumos().size();

        int consumoPromedio = consumoTotal / size;

        System.out.println("El dispositivo " + airePersistido.getNombre() + " tuvo un consumo promedio de " + consumoPromedio + " watts.");

    }

    @Test
    public void consumoPromedioPorTransformadorTest() {
        // 3- Dado un transformador y un período, mostrar su consumo promedio.
    }

    @Test
    public void incrementoConsumoTest() {
        // 4- Recuperar un dispositivo asociado a un hogar de ese transformador
        //   e incrementar un 1000 % el consumo para ese período.
        //   Persistir el dispositivo.
        //   Nuevamente mostrar el consumo para ese transformador.
    }
}
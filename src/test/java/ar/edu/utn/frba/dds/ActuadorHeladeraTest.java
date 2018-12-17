package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.actuador.ActuadorHeladera;
import ar.edu.utn.frba.dds.dispositivo.DIFactory;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteHeladera;
import ar.edu.utn.frba.dds.dispositivo.estadosDispositivo.Context;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ActuadorHeladeraTest {

    private DispositivoInteligente heladera;
    private DIFactory fm;
    private Actuador actuador;

    @Before
    public void setUp() throws Exception {
        fm = new DIFactory();
        heladera  = fm.crearDispositivo("Heladera", 20, "activo",false);
        actuador = this.heladera.getActuador();
    }

    @Test
    public void creacionDispositivoTest() {
        assert(heladera instanceof DispositivoInteligenteHeladera);
    }

    @Test
    public void creacionActuadorTest() {
        assert(actuador instanceof ActuadorHeladera);
    }

    @Test
    public void apagarHeladeraTest() {
        actuador.apagarDispositivo(null);
        assertEquals(heladera.getEstado(),Context.string_Apagado);
    }

    @Test
    public void prenderHeladeraTest() {
        actuador.prenderDispositivo(null);
        assertEquals(heladera.getEstado(),Context.string_Activo);
    }

    @Test
    public void heladeraModoAhorroTest() {
        actuador.cambiarModoAAhorro(null);
        assertEquals(heladera.getEstado(),Context.string_Ahorrro);
    }

    @Test
    public void subirIntensidadTest() {
        ActuadorHeladera actuadorHel = (ActuadorHeladera) actuador;
        DispositivoInteligenteHeladera hel = (DispositivoInteligenteHeladera) heladera;

        actuadorHel.subirIntensidad(10);

        assert(hel.getIntensidad() == 10);
    }

    @Test
    public void bajarIntensidadTest() {
        ActuadorHeladera actuadorHel = (ActuadorHeladera) actuador;
        DispositivoInteligenteHeladera hel = (DispositivoInteligenteHeladera) heladera;

        actuadorHel.subirIntensidad(50);
        actuadorHel.bajarIntensidad(30);

        assert(hel.getIntensidad() == 20);
    }
}
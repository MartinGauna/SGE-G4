package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.actuador.ActuadorHeladera;
import ar.edu.utn.frba.dds.dispositivo.DIFactoryMethod;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteHeladera;
import org.junit.Before;
import org.junit.Test;

public class ActuadorHeladeraTest {

    private DispositivoInteligente heladera;
    private DIFactoryMethod fm;
    private Actuador actuador;

    @Before
    public void setUp() throws Exception {
        fm = new DIFactoryMethod();
        heladera  = fm.crearDispositivo("Heladera", 20, "activo");
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
        actuador.apagarDispositivo();
        assert(heladera.getEstado().equals("apagado"));
    }

    @Test
    public void prenderHeladeraTest() {
        actuador.prenderDispositivo();
        assert(heladera.getEstado().equals("encendido"));
    }

    @Test
    public void heladeraModoAhorroTest() {
        actuador.cambiarModoAAhorro();
        assert(heladera.getEstado().equals("ahorro"));
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
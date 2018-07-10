package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.jsonParser.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AdapterSimplexTest {
private Cliente client;
private List<DispositivoInteligente> dispInt ;
private AdapterSimplex adapterSimplex;
@Before
    public void setUp() throws Exception {
        JsonParser parser = new JsonParser();

        dispInt = parser.loadDispositivosInteligentesJSON("/listaDispositivosInteligentes.json");
    }

    @Test
    public void reporteConsumoEficienteTest()
    {

        //adapterSimplex.reporteConsumoEficiente(this.dispInt);
      // DispositivoInteligente d = new DispositivoInteligente("Aire acondicionado", 1.02, "apagado");
    }
}
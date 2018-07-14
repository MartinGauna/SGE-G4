package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.Config;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.helpers.simplex.facade.SimplexFacade;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AdapterSimplex {

    public PointValuePair solucion;
    Config config = new Config();
    int consumoTotalOptimo = parseInt(config.getProperty("consumoTotalOptimo"));


    public List<DispositivoInteligente> getDispositivosParaApagar(List<DispositivoInteligente> dispositivosInteligentes)
    {
        List<DispositivoInteligente> dispositivosParaApagar = new ArrayList<DispositivoInteligente>();

        try {
            reporteConsumoEficiente(dispositivosInteligentes);

            for (int i = 0; i < dispositivosInteligentes.size(); i++)
            {
                if(dispositivosInteligentes.get(i).getConsumoTotal() > solucion.getPoint()[i])
                {
                    dispositivosParaApagar.add(dispositivosInteligentes.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dispositivosParaApagar;
    }
    public void reporteConsumoEficiente(List<DispositivoInteligente> dispositivosInteligentes) {

        SimplexFacade simplexFacade = new SimplexFacade(GoalType.MAXIMIZE, true);

        double[] restricciones = new double[dispositivosInteligentes.size()];

        //INICIALIZO FUNCION ECONOMICA
        double[] funcionEconomica = new double[restricciones.length];
        for (int i = 0; i < restricciones.length; i++)
        {
            funcionEconomica[i] = 1;
        }
        simplexFacade.crearFuncionEconomica(funcionEconomica);


        /**
         - >= : Relationship.GEQ
         - <= : Relationship.LEQ
         - =  : Relationship.EQ
         **/


        //RESTRICCION INICIAL
        for (int i = 0; i < restricciones.length; i++) {

                restricciones[i] = dispositivosInteligentes.get(i).getConsumoHora();
        }

        simplexFacade.agregarRestriccion(Relationship.LEQ, consumoTotalOptimo, restricciones);

        //RESTRICCION INDIVIDUALES
        for (int i = 0; i < restricciones.length; i++) {
                double[] restricciones2 = new double[dispositivosInteligentes.size()];

                for(int z = 0; z < dispositivosInteligentes.size();z++)
                {if(z==i){restricciones2[z] = 1;} else{restricciones2[z]=0;}}

                double max = dispositivosInteligentes.get(i).uso_maximo;
                double min = dispositivosInteligentes.get(i).uso_minimo;

                //MIN
                simplexFacade.agregarRestriccion(Relationship.GEQ,  min,restricciones2);

                //MAX
                simplexFacade.agregarRestriccion(Relationship.LEQ, max,restricciones2);
        }


        try {
            solucion = simplexFacade.resolver();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.helpers.simplex.facade.SimplexFacade;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.List;
public class AdapterSimplex {

    PointValuePair solucion;


    public void reporteConsumoEficiente(List<DispositivoInteligente> dispositivosInteligentes) {
        SimplexFacade simplexFacade = new SimplexFacade(GoalType.MAXIMIZE, true);

        simplexFacade.crearFuncionEconomica(1, 1, 1);

        /**
         - >= : Relationship.GEQ
         - <= : Relationship.LEQ
         - =  : Relationship.EQ
         **/


        //RESTRICCION INICIAL
        double[] restricciones = new double[dispositivosInteligentes.size()];

        for (int i = 0; i < restricciones.length; i++) {

                restricciones[i] = dispositivosInteligentes.get(i).getConsumoHora();

        }

        simplexFacade.agregarRestriccion(Relationship.LEQ, 440640, restricciones);

        //RESTRICCION INDIVIDUALES
        for (int i = 0; i < restricciones.length; i++) {
                double[] restricciones2 = new double[dispositivosInteligentes.size()];

                for(int z = 0; z < dispositivosInteligentes.size();z++)
                {if(z==i){restricciones2[z] = 1;} else{restricciones2[z]=0;}}

                double max = dispositivosInteligentes.get(i).getUso_maximo();
                double min = dispositivosInteligentes.get(i).getUso_minimo();

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

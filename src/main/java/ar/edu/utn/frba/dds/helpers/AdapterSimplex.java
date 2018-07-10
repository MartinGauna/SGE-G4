package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.helpers.simplex.facade.SimplexFacade;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.List;

public class AdapterSimplex {


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

        for (int i = 0; i <= dispositivosInteligentes.size(); i++) {

                restricciones[i] = dispositivosInteligentes.get(i).getConsumoHora();

        }

        simplexFacade.agregarRestriccion(Relationship.LEQ, 440640, restricciones);

        //RESTRICCION INDIVIDUALES
        for (int i = 0; i <= dispositivosInteligentes.size(); i++) {
                double[] restricciones2 = new double[dispositivosInteligentes.size()];

                for(int z = 0; z <= dispositivosInteligentes.size();z++)
                {if(z==i){restricciones2[z] = 1;} else{restricciones2[z]=0;}}


                //MAX
                simplexFacade.agregarRestriccion(Relationship.LEQ, dispositivosInteligentes.get(i).getUso_maximo(),restricciones2);
                //MIN
                simplexFacade.agregarRestriccion(Relationship.GEQ,dispositivosInteligentes.get(i).getUso_minimo(),restricciones2);
        }


        try {
            PointValuePair solucion = simplexFacade.resolver();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.helpers.simplex.facade.SimplexFacade;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import java.util.List;

public class AdapterSimplex {


    public void reporteConsumoEficiente(Cliente cliente) {
        SimplexFacade simplexFacade = new SimplexFacade(GoalType.MAXIMIZE, true);

        simplexFacade.crearFuncionEconomica(1, 1, 1);

        /**
         - >= : Relationship.GEQ
         - <= : Relationship.LEQ
         - =  : Relationship.EQ
         **/

        List<Dispositivo> dispositivos = cliente.getDispositivos();
        int cantDispositivosInteligentes = 0;

        //RESTRICCION INICIAL
        for (Dispositivo disp : dispositivos) {
            if (disp instanceof DispositivoInteligente) {
                cantDispositivosInteligentes++;
            }
        }
        double[] restricciones = new double[cantDispositivosInteligentes];

        for (int i = 0; i <= dispositivos.size(); i++) {

            if (dispositivos.get(i) instanceof DispositivoInteligente) {
                restricciones[i] = dispositivos.get(i).getConsumoHora();
            }
        }

        simplexFacade.agregarRestriccion(Relationship.LEQ, 440640, restricciones);

        //RESTRICCION INDIVIDUALES
        for (int i = 0; i <= dispositivos.size(); i++) {
                double[] restricciones2 = new double[cantDispositivosInteligentes];

                for(int z = 0; z <= dispositivos.size();z++)
                {if(z==i){restricciones2[z] = 1;} else{restricciones2[z]=0;}}


                //MAX
                simplexFacade.agregarRestriccion(Relationship.LEQ, dispositivos.get(i).getUso_maximo(),restricciones2);
                //MIN
                simplexFacade.agregarRestriccion(Relationship.GEQ,dispositivos.get(i).getUso_minimo(),restricciones2);
        }


        try {
            PointValuePair solucion = simplexFacade.resolver();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

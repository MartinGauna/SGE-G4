package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.Config;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
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


    public List<Dispositivo> getDispositivosParaApagar(List<Dispositivo> dispositivos)
    {
        List<Dispositivo> dispositivosParaApagar = new ArrayList<Dispositivo>();

        try {
            reporteConsumoEficiente(dispositivos);

            for (int i = 0; i < dispositivos.size(); i++)
            {
                if(dispositivos.get(i).getConsumoTotal() > solucion.getPoint()[i])
                {
                    dispositivosParaApagar.add(dispositivos.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dispositivosParaApagar;
    }
    public void reporteConsumoEficiente(List<Dispositivo> dispositivos) {

        SimplexFacade simplexFacade = new SimplexFacade(GoalType.MAXIMIZE, true);

        double[] restricciones = new double[dispositivos.size()];

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

                restricciones[i] = dispositivos.get(i).getConsumoHora();
        }

        simplexFacade.agregarRestriccion(Relationship.LEQ, consumoTotalOptimo, restricciones);

        //RESTRICCION INDIVIDUALES
        for (int i = 0; i < restricciones.length; i++) {
                double[] restricciones2 = new double[dispositivos.size()];

                for(int z = 0; z < dispositivos.size();z++)
                {if(z==i){restricciones2[z] = 1;} else{restricciones2[z]=0;}}

                double max = dispositivos.get(i).getUsoMaximo();
                double min = dispositivos.get(i).getUsoMinimo();

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
    public void reporteConsumoEficienteDispositivoGenerico(List<Dispositivo> dispositivos) {

        SimplexFacade simplexFacade = new SimplexFacade(GoalType.MAXIMIZE, true);

        double[] restricciones = new double[dispositivos.size()];

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

            restricciones[i] = dispositivos.get(i).getConsumoHora();
        }

        simplexFacade.agregarRestriccion(Relationship.LEQ, consumoTotalOptimo, restricciones);

        //RESTRICCION INDIVIDUALES
        for (int i = 0; i < restricciones.length; i++) {
            double[] restricciones2 = new double[dispositivos.size()];

            for(int z = 0; z < dispositivos.size();z++)
            {if(z==i){restricciones2[z] = 1;} else{restricciones2[z]=0;}}

            double max = dispositivos.get(i).getUsoMaximo();
            double min = dispositivos.get(i).getUsoMinimo();

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

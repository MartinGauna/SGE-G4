
package ar.edu.utn.frba.dds.helpers.simplex.facade;

import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Collection;

public class SimplexFacade {
	private SimplexSolver simplex;
	private LinearObjectiveFunction funcionEconomica;
	private Collection<LinearConstraint> restricciones;
	private GoalType objetivo;
	private boolean variablesPositivas;
	
	public SimplexFacade(GoalType objetivo, boolean variablesPositivas) {
		this.variablesPositivas = variablesPositivas;
		this.objetivo = objetivo;
		this.restricciones = new ArrayList<LinearConstraint>();
		this.simplex = new SimplexSolver();
	}
	
	public void crearFuncionEconomica(double ... coeficientes) {
		this.funcionEconomica = new LinearObjectiveFunction(coeficientes, 0);
	}
	
	public void agregarRestriccion(Relationship unComparador, double valorAcomprar, double ... coeficientes) {
		this.restricciones.add(new LinearConstraint(coeficientes,unComparador, valorAcomprar));
	}
	
	public PointValuePair resolver() throws TooManyIterationsException{
		return this.simplex.optimize(
										new MaxIter(100),
										this.funcionEconomica,
										new LinearConstraintSet(this.restricciones),
										this.objetivo,
										new NonNegativeConstraint(this.variablesPositivas)
				);
	}
}

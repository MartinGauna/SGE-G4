package ar.edu.utn.frba.dds.regla;

import ar.edu.utn.frba.dds.actuador.Actuador;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Regla implements Observer{

    protected Actuador actuador;
    protected String methodname;
    public List<Condicion> condiciones;

    public Regla(Actuador actuador, String methodname,  List<Condicion> condiciones) {
        this.actuador = actuador;
        this.methodname = methodname;
        this.condiciones = condiciones;
    }
    public void update(Observable observable){}

    public void ejecutar(){
        char previous_logic_factor;
        //Resultado del update es TRUE por default (por si no tiene ninguna condicion)
        boolean result = true;

        //Iteramos las condiciones
        for (Condicion condicion : condiciones) {
            previous_logic_factor = condicion.getPrevious_logic_factor();
            //Si el factor es OR
            if (previous_logic_factor == '|')
            {
                //Si la condicion no es verdadera entonces setear result en false
                if (!condicion.evaluar() && result == false) {
                    result = false;
                }
                if(result == true)
                {break;}
                else{result = true;}
            }

            //Si el factor es AND entonces me fijo cual fue el ultimo resultado de las condiciones anteriores
            if (previous_logic_factor == '&')
            {
                //Si el resultado anterior es FALSO entonces salgo del FOR EACH
                if(!result){break;}

                //Si la condicion no es verdadera entonces setear result en false
                if (!condicion.evaluar()) {
                    result = false;
                }
            }

            else
            {
                if (!condicion.evaluar()) {
                    result = false;
                }
            }
        }

        //Si las condiciones dan True --> ejecuto el metodo pedido
        if(result)
        {
            try {
            //Obtener el metodo con el nombre pasado para el actuador
            actuador.getClass().getDeclaredMethod(methodname).invoke(actuador);
            } catch (IllegalAccessException e) {
            e.printStackTrace();
            } catch (InvocationTargetException e) {
            e.printStackTrace();
            } catch (NoSuchMethodException e) {
            e.printStackTrace();
            }
        }

    }
}
package ar.edu.utn.frba.dds.regla;

import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Entity
@Table
public class Regla implements Observer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

;    @OneToOne(fetch = FetchType.LAZY, targetEntity = Actuador.class)
    @JoinColumn(name = "idActuador", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR"))
    private Actuador actuador;

    @NotNull
    protected String methodname;

    @NotNull
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idRegla", referencedColumnName = "id")
    public List<Condicion> condiciones;

    public Regla(Actuador actuador, String methodname,  List<Condicion> condiciones) {
        this.actuador = actuador;
        this.methodname = methodname;
        this.condiciones = condiciones;
    }

    public Regla() {
    }

    public void update(Observable observable){}

    public int getId() {
        return id;
    }
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
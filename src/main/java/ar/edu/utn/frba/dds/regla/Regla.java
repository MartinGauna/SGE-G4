package ar.edu.utn.frba.dds.regla;

import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Regla implements Observer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Actuador.class)
    @JoinColumn(name = "idActuador", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR"))
    private Actuador actuador;

    @NotNull
    protected String methodname;

    @NotNull
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idRegla", referencedColumnName = "id")
    public List<Condicion> condiciones;

    public Regla(DispositivoInteligente dispositvo, String methodname,  List<Condicion> condiciones) {
        Actuador actuador1 = new Actuador(dispositvo);
        actuador = actuador1;
        this.methodname = methodname;
        if(condiciones != null) {
            this.condiciones = condiciones;
        } else {
            this.condiciones = new ArrayList<Condicion>();
        }
    }

    public void addCondiciones(List<Condicion> condiciones)
    {
        for (Condicion c:condiciones)
        {
            this.condiciones.add(c);
        }
    }
    public void addCondicion(Condicion condicion)
    {
        this.condiciones.add(condicion);
    }
    public Regla() {
    }

    public void update(Observable observable){}

    public int getId() {
        return id;
    }

    public String getMethodName() {
        return methodname;
    }

    public int getActuadorID() {
        return actuador.getId();
    }

    public Actuador getActuador() {
        return actuador;
    }

    public void ejecutar(){
        //Resultado del update es TRUE por default (por si no tiene ninguna condicion)
        boolean result = true;

        //Iteramos las condiciones
        //for (Condicion condicion : condiciones) {
        for (int i = 0; i < condiciones.size(); i++){
            Condicion condicion = condiciones.get(i);
            //Si el factor es OR

                //Si el resultado anterior es FALSO entonces salgo del FOR EACH
                if(!result){break;}

                //Si la condicion no es verdadera entonces setear result en false
                if (!condicion.evaluar()) {
                    result = false;
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
                actuador.getClass().getDeclaredMethod(methodname, DispositivoInteligente.class).invoke(actuador, actuador.getDispositivo());
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
package ar.edu.utn.frba.dds.regla;

import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Regla implements Observer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Actuador.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "idActuador", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR"))
    private Actuador actuador;

    public void setMethodname(@NotNull String methodname) {
        this.methodname = methodname;
    }

    @NotNull
    protected String methodname;
    private int cantidad;

    @NotNull
    public List<Condicion> getCondiciones() {
        return condiciones;
    }

    @NotNull
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idRegla", referencedColumnName = "id")
    public List<Condicion> condiciones;

    public Regla(DispositivoInteligente dispositvo, String methodname,  List<Condicion> condiciones, int cantidad) {
        Actuador actuador1 = new Actuador(dispositvo);
        actuador = actuador1;
        this.methodname = methodname;
        if(condiciones != null) {
            this.condiciones = condiciones;
        } else {
            this.condiciones = new ArrayList<Condicion>();
        }
        this.cantidad = cantidad;
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

    public void ejecutar() {
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
            switch (methodname){
                case "prenderDispositivo":
                    actuador.prenderDispositivo();
                    break;
                case "apagarDispositivo":
                    actuador.apagarDispositivo();
                    break;
                case "cambiarModoAAhorro":
                    actuador.cambiarModoAAhorro();
                    break;
                case "subir":
                    actuador.subir(cantidad);
                    break;
                case "bajar":
                    actuador.bajar(cantidad);
                    break;
            }
        }

    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
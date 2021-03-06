package ar.edu.utn.frba.dds.regla;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Condicion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Regla.class)
    @JoinColumn(name = "idRegla", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_REGLA"))
    private Regla regla;

    @NotNull
    private char criterio;

    @NotNull
    private char previous_logic_factor; //AND, OR, END

    @NotNull
    private long magnitudDelSensor;

    @NotNull
    private long valor_condicion;

    public Condicion(char criterio,  long magnitudDelSensor, long valor_condicion, char previous_logic_factor) {
        this.criterio = criterio;
        this.magnitudDelSensor = magnitudDelSensor;
        this.valor_condicion = valor_condicion;
        this.previous_logic_factor = previous_logic_factor;
    }
    public Condicion(char criterio,  long magnitudDelSensor, long valor_condicion) {
        this.criterio = criterio;
        this.magnitudDelSensor = magnitudDelSensor;
        this.valor_condicion = valor_condicion;
    }

    public Condicion() {
    }

    public char getPrevious_logic_factor() {
        return previous_logic_factor;
    }

    boolean evaluar ()
    {
        boolean output = false;

        switch (criterio){
            case '=':
                if(magnitudDelSensor == valor_condicion)
                {
                    output = true;
                }
                break;
            case '>':
                if(magnitudDelSensor > valor_condicion)
                {
                    output = true;
                }
                break;
            case '<':
                if(magnitudDelSensor < valor_condicion)
                {
                    output = true;
                }
                break;
        }

        return output;
    }

    public int getId() {
        return id;
    }

    public char getCriterio() {
        return criterio;
    }

    public long getMagnitudDelSensor() {
        return magnitudDelSensor;
    }

    public long getValor_condicion() {
        return valor_condicion;
    }

    public void setRegla(Regla regla) {
        this.regla = regla;
    }

    public void setCriterio(char criterio) {
        this.criterio = criterio;
    }

    public void setPrevious_logic_factor(char previous_logic_factor) {
        this.previous_logic_factor = previous_logic_factor;
    }

    public void setMagnitudDelSensor(long magnitudDelSensor) {
        this.magnitudDelSensor = magnitudDelSensor;
    }

    public void setValor_condicion(long valor_condicion) {
        this.valor_condicion = valor_condicion;
    }
}

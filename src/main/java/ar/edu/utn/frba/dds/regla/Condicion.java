package ar.edu.utn.frba.dds.regla;

public class Condicion {

    private char criterio;
    private char previous_logic_factor; //AND, OR, END
    private long magnitudDelSensor;
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
}

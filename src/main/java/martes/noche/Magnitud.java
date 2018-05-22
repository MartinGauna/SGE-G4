package martes.noche;

public class Magnitud {

    private long valor;
    private String magnitud;

    /**
     * Getters & Setters
     */
    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }
    /*====================================================*/

    public Magnitud (){
        this.valor =  0;
        this.magnitud = "";
    }

    public Magnitud (long valor, String magnitud){
        this.valor =  valor;
        this.magnitud = magnitud;
    }
}

package martes.noche;

public class Magnitud {
    private long valor;
    private String magnitud;

    public Magnitud (){
        this.valor =  0;
        this.magnitud = "";
    }

    public Magnitud (long valor, String magnitud){
        this.valor =  valor;
        this.magnitud = magnitud;
    }
}

package ar.edu.utn.frba.dds.exception;

public class IncompleteFormException extends RuntimeException {

    public IncompleteFormException(){
        super("Verifique que haya completado todos los campos");
    }

    public IncompleteFormException(String message){super(message);}

}

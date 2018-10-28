package ar.edu.utn.frba.dds.exception;

public class DuplicateValueException extends RuntimeException {

    public DuplicateValueException(){
        super("Valor ya existente en el storage");
    }

    public DuplicateValueException(String ex){
        super(ex);
    }
}

package ar.edu.utn.frba.dds.exception;

public class DeleteException extends RuntimeException {

    public DeleteException(){
        super("No se pudo borrar el registro");
    }

    public DeleteException(String message){
        super(message);
    }

}

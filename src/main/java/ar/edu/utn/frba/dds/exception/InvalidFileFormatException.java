package ar.edu.utn.frba.dds.exception;

/**
 * Created by pablomorrone on 11/4/17.
 */
public class InvalidFileFormatException extends RuntimeException {

    public InvalidFileFormatException(){
        super();
    }

    public InvalidFileFormatException(String e){
        super(e);
    }

}

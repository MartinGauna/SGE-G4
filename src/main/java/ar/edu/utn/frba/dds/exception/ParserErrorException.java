package ar.edu.utn.frba.dds.exception;

/**
 * Created by pablomorrone on 17/4/17.
 */
public class ParserErrorException extends RuntimeException {

    public ParserErrorException(){
        super();
    }

    public ParserErrorException(String e){
        super(e);
    }
}

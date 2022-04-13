package wniemiec.mobilang.ama.parser.exception;


/**
 * Signals that there is no desired instance in a parser factory.
 */
public class FactoryException extends Exception {
    
    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public FactoryException() {
    }

    public FactoryException(String message) {
        super(message);
    }
}

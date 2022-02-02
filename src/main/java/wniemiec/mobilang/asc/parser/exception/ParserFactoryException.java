package wniemiec.mobilang.asc.parser.exception;


/**
 * Signals that there is no desired instance in a parser factory.
 */
public class ParserFactoryException extends Exception {
    
    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public ParserFactoryException() {
    }

    public ParserFactoryException(String message) {
        super(message);
    }
}

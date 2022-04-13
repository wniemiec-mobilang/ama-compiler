package wniemiec.mobilang.ama.parser.exception;


/**
 * Signals that parsing failed.
 */
public class ParseException extends Exception {

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }
}

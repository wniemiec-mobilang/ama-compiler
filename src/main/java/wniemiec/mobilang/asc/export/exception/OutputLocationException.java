package wniemiec.mobilang.asc.export.exception;


/**
 * Signals that output location is invalid.
 */
public class OutputLocationException extends Exception {

    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public OutputLocationException(String message) {
        super(message);
    }

    public OutputLocationException() {
        super();
    }
}

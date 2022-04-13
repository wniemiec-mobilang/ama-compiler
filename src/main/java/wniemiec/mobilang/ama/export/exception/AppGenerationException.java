package wniemiec.mobilang.ama.export.exception;


/**
 * Signals that creating a mobile application failed.
 */
public class AppGenerationException extends Exception {

    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public AppGenerationException(String message) {
        super(message);
    }

    public AppGenerationException() {
        super();
    }
}

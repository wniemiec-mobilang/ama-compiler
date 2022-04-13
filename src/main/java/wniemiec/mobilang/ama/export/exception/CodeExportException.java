package wniemiec.mobilang.ama.export.exception;


/**
 * Signals that code exportation failed.
 */
public class CodeExportException extends Exception {

    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public CodeExportException(String message) {
        super(message);
    }

    public CodeExportException() {
        super();
    }
}

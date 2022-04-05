package wniemiec.mobilang.asc.coder.exception;

import java.util.List;

import wniemiec.util.java.StringUtils;


/**
 * Signals that code generation failed.
 */
public class CoderException extends Exception {

    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public CoderException() {
    }

    public CoderException(String message) {
        super(message);
    }

    public CoderException(List<String> log) {
        super(StringUtils.implode(log, "\n"));
    }
}

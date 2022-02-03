package wniemiec.mobilang.asc.framework.coder;

import java.util.Collection;
import java.util.List;


/**
 * Responsible for generating core code of a framework.
 */
import wniemiec.mobilang.asc.models.FileCode;

public abstract class FrameworkCoreCoder {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    protected Collection<String> screensName;

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    protected FrameworkCoreCoder(Collection<String> screensName) {
        this.screensName = screensName;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public abstract List<FileCode> generateCode();
}

package wniemiec.mobilang.asc.framework.coder;

import java.util.List;

import wniemiec.mobilang.asc.coder.exception.CoderException;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.ScreenData;


/**
 * Responsible for generating screens code of a framework.
 */
public abstract class FrameworkScreensCoder {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    protected final List<ScreenData> screensData;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    protected FrameworkScreensCoder(List<ScreenData> screensData) {
        this.screensData = screensData;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public abstract List<FileCode> generateCode() throws CoderException;
}

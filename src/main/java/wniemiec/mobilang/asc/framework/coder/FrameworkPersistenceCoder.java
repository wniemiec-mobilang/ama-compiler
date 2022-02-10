package wniemiec.mobilang.asc.framework.coder;

import java.util.List;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.persistence.PersistenceData;


/**
 * Responsible for generating persistence code of a framework.
 */
public abstract class FrameworkPersistenceCoder {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    protected PersistenceData persistenceData;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    protected FrameworkPersistenceCoder(PersistenceData persistenceData) {
        this.persistenceData = persistenceData;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public abstract List<FileCode> generateCode();
}

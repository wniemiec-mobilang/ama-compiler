package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.asc.framework.coder.FrameworkPersistenceCoder;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PersistenceData;


/**
 * Responsible for generating persistence code of React Native framework.
 */
public class ReactNativeFrameworkPersistenceCoder extends FrameworkPersistenceCoder {

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeFrameworkPersistenceCoder(PersistenceData persistenceData) {
        super(persistenceData);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public List<FileCode> generateCode() {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

}

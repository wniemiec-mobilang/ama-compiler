package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.util.ArrayList;
import java.util.List;
import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.framework.coder.FrameworkPersistenceCoder;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PersistenceData;


/**
 * Responsible for generating React Native framework code for persistence.
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
        Consolex.writeWarning("React Native persistence coder has not been implemented yet");

        return new ArrayList<>();
    }

}

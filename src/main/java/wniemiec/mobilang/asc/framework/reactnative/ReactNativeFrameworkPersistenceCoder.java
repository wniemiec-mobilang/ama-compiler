package wniemiec.mobilang.asc.framework.reactnative;

import java.util.ArrayList;
import java.util.List;

import wniemiec.mobilang.asc.framework.FrameworkPersistenceCoder;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PersistenceData;

public class ReactNativeFrameworkPersistenceCoder extends FrameworkPersistenceCoder {

    public ReactNativeFrameworkPersistenceCoder(PersistenceData persistenceData) {
        super(persistenceData);
    }

    @Override
    public List<FileCode> generateCode() {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

}

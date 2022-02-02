package wniemiec.mobilang.asc.framework.coder;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PersistenceData;

public abstract class FrameworkPersistenceCoder {

    protected PersistenceData persistenceData;

    protected FrameworkPersistenceCoder(PersistenceData persistenceData) {
        this.persistenceData = persistenceData;
    }
    
    public abstract List<FileCode> generateCode();
}

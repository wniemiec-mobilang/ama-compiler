package wniemiec.mobilang.asc.framework.manager;

import java.io.IOException;
import java.nio.file.Path;

import wniemiec.mobilang.asc.models.PropertiesData;

public abstract class FrameworkProjectManager {

    protected Path workingDirectory;

    protected FrameworkProjectManager(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }
    
    public abstract void create(PropertiesData propertiesData) throws IOException;
    public abstract void addDependency(String dependency)  throws IOException;
}

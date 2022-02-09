package wniemiec.mobilang.asc.framework.manager;

import java.io.IOException;
import java.nio.file.Path;
import wniemiec.mobilang.asc.models.PropertiesData;


/**
 * Responsible for project management of a framework.
 */
public abstract class FrameworkProjectManager {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    protected Path workingDirectory;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    protected FrameworkProjectManager(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public abstract void create(PropertiesData propertiesData) throws IOException;
    public abstract void addDependency(String dependency)  throws IOException;
}

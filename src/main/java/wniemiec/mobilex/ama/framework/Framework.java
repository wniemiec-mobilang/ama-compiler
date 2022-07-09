package wniemiec.mobilex.ama.framework;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.export.exception.AppGenerationException;
import wniemiec.mobilex.ama.models.Project;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.models.Screen;


/**
 * Responsible for defining a framework compatible with Abstract Syntax Tree to 
 * Mobile Application (AMA) compiler. The framework class must be in a package
 * with its name in lower case.
 */
public interface Framework {

    /**
     * Creates a new framework project.
     * 
     * @param       properties Application information
     * @param       location Path where the project will be created
     * 
     * @throws      IOException If project cannot be created
     */
    void createProject(Properties properties, Path location) throws IOException;
  
    /**
     * Adds a dependency on the project.
     * 
     * @param       dependency Dependency name
     * @param       location Path where the project is
     * 
     * @throws      IOException If dependency cannot be added
     */
    void addProjectDependency(String dependency, Path location) throws IOException;

    /**
     * Generates application code.
     * 
     * @param       screens Information about application screens 
     * 
     * @return      Code files along with the necessary dependencies
     * 
     * @throws      CoderException If code cannot be generated
     */
    Project generateCode(List<Screen> screens) throws CoderException;

    /**
     * Generates mobile application for a platform.
     * 
     * @param       platform Mobile platform that the application will be 
     * generated
     * @param       source Path where source code is
     * @param       output Path where mobile application will be created
     * 
     * @throws      AppGenerationException If mobile application cannot be 
     * created
     */
    void generateMobileApplicationFor(String platform, Path source, Path output) 
    throws AppGenerationException;
}

package wniemiec.mobilex.ama.framework.reactnative;

import java.io.IOException;
import java.nio.file.Path;
import wniemiec.io.java.Terminal;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.util.io.FileManager;


/**
 * Responsible for project management of React Native framework.
 */
class ReactNativeProjectManager {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Terminal terminal;
    private FileManager fileManager;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeProjectManager(Terminal terminal, FileManager fileManager) {
        validateTerminal(terminal);
        validateFileManager(fileManager);
        
        this.terminal = terminal;
        this.fileManager = fileManager;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void validateTerminal(Terminal instance) {
        if (instance == null) {
            throw new IllegalArgumentException("Terminal cannot be null");
        }
    }

    private void validateFileManager(FileManager instance) {
        if (instance == null) {
            throw new IllegalArgumentException("File manager cannot be null");
        }
    }

    public void createProject(Properties properties, Path location) 
    throws IOException {
        validateProperties(properties);
        validateLocation(location);

        runReactNativeInit(properties, location);
        removeOldAppFile(location);
    }

    private void validateProperties(Properties instance) {
        if (instance == null) {
            throw new IllegalArgumentException("Properties cannot be null");
        }
    }

    private void validateLocation(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
    }

    private void runReactNativeInit(Properties properties, Path location) 
    throws IOException {
        generateReactNativeProject(properties);
        removeAptGeneratedFolder(properties);
        moveProjectFolderTo(properties, location);
    }

    private void generateReactNativeProject(Properties properties) 
    throws IOException {
        terminal.exec(
            "react-native", 
            "init", 
            properties.getApplicationName()
        );
    }

    private void removeAptGeneratedFolder(Properties properties) 
    throws IOException {
        Path location = Path.of(properties.getApplicationName());
        
        fileManager.removeFile(location.resolve(".apt_generated"));
    }

    private void moveProjectFolderTo(Properties properties, Path location) 
    throws IOException {
        terminal.exec(
            "mv", 
            properties.getApplicationName(), 
            location.getFileName().toString()
        );

        terminal.exec(
            "mv",
            location.getFileName().toString(),
            location.getParent().toString()
        );
    }

    private void removeOldAppFile(Path location) throws IOException {
        fileManager.removeFile(location.resolve("App.js"));
    }

    public void addProjectDependency(String dependency, Path projectLocation)
    throws IOException {
        validateDependency(dependency);
        validateLocation(projectLocation);

        for (String dependencyName : dependency.split(" ")) {
            terminal.exec(
                "npm", 
                "install", 
                "--prefix",
                projectLocation.toString(),
                "--save", 
                dependencyName
            );
        }
    }

    private void validateDependency(String dependency) {
        if (dependency == null) {
            throw new IllegalArgumentException("Dependency cannot be null");
        }
    }
}

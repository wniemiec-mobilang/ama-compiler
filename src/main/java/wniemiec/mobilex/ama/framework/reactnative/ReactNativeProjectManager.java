package wniemiec.mobilex.ama.framework.reactnative;

import java.io.IOException;
import java.nio.file.Path;
import wniemiec.io.java.Terminal;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.util.data.Validator;
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
        Validator.validateTerminal(terminal);
        Validator.validateFileManager(fileManager);
        
        this.terminal = terminal;
        this.fileManager = fileManager;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    

    public void createProject(Properties properties, Path location) 
    throws IOException {
        Validator.validateProperties(properties);
        Validator.validateLocation(location);

        runReactNativeInit(properties, location);
        removeOldAppFile(location);
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
        Validator.validateDependency(dependency);
        Validator.validateLocation(projectLocation);

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
}

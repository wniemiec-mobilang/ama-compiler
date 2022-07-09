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
        this.terminal = terminal;
        this.fileManager = fileManager;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void createProject(Properties propertiesData, Path location) 
    throws IOException {
        runReactNativeInit(propertiesData, location);
        removeOldAppFile(location);
    }

    private void runReactNativeInit(Properties propertiesData, Path location) 
    throws IOException {
        generateReactNativeProject(propertiesData);
        removeAptGeneratedFolder(propertiesData);
        moveProjectFolderTo(propertiesData, location);
    }

    private void generateReactNativeProject(Properties propertiesData) 
    throws IOException {
        terminal.exec(
            "react-native", 
            "init", 
            propertiesData.getApplicationName()
        );
    }

    private void removeAptGeneratedFolder(Properties propertiesData) 
    throws IOException {
        Path location = Path.of(propertiesData.getApplicationName());
        
        fileManager.removeFile(location.resolve(".apt_generated"));
    }

    private void moveProjectFolderTo(Properties propertiesData, Path location) 
    throws IOException {
        terminal.exec(
            "mv", 
            propertiesData.getApplicationName(), 
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

    public void addProjectDependency(String dependency, Path projectLocation) throws IOException {
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

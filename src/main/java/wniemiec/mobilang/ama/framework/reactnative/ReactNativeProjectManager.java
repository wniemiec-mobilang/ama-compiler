package wniemiec.mobilang.ama.framework.reactnative;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.StandardTerminalBuilder;
import wniemiec.io.java.Terminal;
import wniemiec.mobilang.ama.models.PropertiesData;


/**
 * Responsible for project management of React Native framework.
 */
class ReactNativeProjectManager {

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void createProject(PropertiesData propertiesData, Path location) 
    throws IOException {
        runReactNativeInit(propertiesData, location);
        removeOldAppFile(location);
    }

    private void runReactNativeInit(PropertiesData propertiesData, Path location) 
    throws IOException {
        exec(
            "react-native", 
            "init", 
            propertiesData.getAppName()
        );

        removeAptGeneratedFolder(Path.of(propertiesData.getAppName()));

        exec(
            "mv", 
            propertiesData.getAppName(), 
            location.getFileName().toString()
        );

        exec(
            "mv",
            location.getFileName().toString(),
            location.getParent().toString()
        );
    }

    private void removeAptGeneratedFolder(Path location) throws IOException {
        Files.delete(location.resolve(".apt_generated"));
    }

    private void removeOldAppFile(Path location) throws IOException {
        Files.delete(location.resolve("App.js"));
    }

    private void exec(String... command) throws IOException {
        Terminal terminal = StandardTerminalBuilder
            .getInstance()
            .outputHandler(Consolex::writeDebug)
            .outputErrorHandler(Consolex::writeDebug)
            .build();
        
        terminal.exec(command);
    }

    public void addProjectDependency(String dependency, Path projectLocation) throws IOException {
        for (String dependencyName : dependency.split(" ")) {
            exec(
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

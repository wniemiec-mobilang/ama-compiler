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
    //		Attributes
    //-------------------------------------------------------------------------
    private Path workingDirectory;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeProjectManager(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void createProject(PropertiesData propertiesData) throws IOException {
        runReactNativeInit(propertiesData);
        removeOldAppFile();
    }

    private void runReactNativeInit(PropertiesData propertiesData) throws IOException {
        exec(
            "react-native", 
            "init", 
            propertiesData.getAppName()
        );

        exec(
            "mv", 
            propertiesData.getAppName(), 
            workingDirectory.getFileName().toString()
        );

        exec(
            "mv",
            workingDirectory.getFileName().toString(),
            workingDirectory.getParent().toString()
        );
    }

    private void removeOldAppFile() throws IOException {
        Files.delete(workingDirectory.resolve("App.js"));
    }

    private void exec(String... command) throws IOException {
        Terminal terminal = StandardTerminalBuilder
            .getInstance()
            .outputHandler(Consolex::writeDebug)
            .outputErrorHandler(Consolex::writeDebug)
            .build();
        
        terminal.exec(command);
    }

    public void addProjectDependency(String dependency) throws IOException {
        for (String dependencyName : dependency.split(" ")) {
            exec(
                "npm", 
                "install", 
                "--prefix",
                workingDirectory.toString(),
                "--save", 
                dependencyName
            );
        }
    }
}

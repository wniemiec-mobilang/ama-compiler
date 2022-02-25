package wniemiec.mobilang.asc.framework.manager.reactnative;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.StandardTerminalBuilder;
import wniemiec.io.java.Terminal;
import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManager;
import wniemiec.mobilang.asc.models.PropertiesData;


/**
 * Responsible for project management of React Native framework.
 */
public class ReactNativeFrameworkProjectManager extends FrameworkProjectManager {

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeFrameworkProjectManager(Path workingDirectory) {
        super(workingDirectory);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void create(PropertiesData propertiesData) throws IOException {
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
            .outputHandler(Consolex::writeInfo)
            .outputErrorHandler(Consolex::writeError)
            .build();
        
        terminal.exec(command);
    }

    @Override
    public void addDependency(String dependency) throws IOException {
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

package wniemiec.mobilang.asc.framework.manager.reactnative;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManager;
import wniemiec.mobilang.asc.models.PropertiesData;
import wniemiec.mobilang.asc.utils.Shell;


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
        exec("react-native init " + propertiesData.getAppName());
        
        workingDirectory = workingDirectory.resolve(propertiesData.getAppName());

        Files.delete(workingDirectory.resolve("App.js"));
    }

    private void exec(String... command) throws IOException {
        Shell shell = new Shell(workingDirectory, true);
        shell.exec(command);
        
        if (shell.hasError()) {
            Consolex.writeError(shell.getErrorOutput());
        }
        else {
            Consolex.writeInfo(shell.getOutput());
        }
    }

    @Override
    public void addDependency(String dependency) throws IOException {
        exec("npm install --save " + dependency);
    }
}

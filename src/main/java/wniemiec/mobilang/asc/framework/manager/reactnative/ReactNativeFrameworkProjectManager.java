package wniemiec.mobilang.asc.framework.manager.reactnative;

import java.io.IOException;
import java.nio.file.Path;

import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManager;
import wniemiec.mobilang.asc.models.PropertiesData;
import wniemiec.mobilang.asc.utils.Shell;

public class ReactNativeFrameworkProjectManager extends FrameworkProjectManager {

    public ReactNativeFrameworkProjectManager(Path workingDirectory) {
        super(workingDirectory);
    }

    @Override
    public void create(PropertiesData propertiesData) throws IOException {
        Shell shell = new Shell(workingDirectory);
        shell.exec("react-native init " + propertiesData.getAppName());
        
        if (shell.hasError()) {
            Consolex.writeError(shell.getErrorOutput());
        }
        else {
            Consolex.writeInfo(shell.getOutput());
        }
    }

    @Override
    public void addDependency(String dependency) throws IOException {
        // TODO Auto-generated method stub
        
    }

}

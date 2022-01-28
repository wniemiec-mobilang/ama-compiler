package wniemiec.mobilang.asc.framework.reactnative;

import java.nio.file.Path;

import wniemiec.mobilang.asc.framework.FrameworkProjectManager;
import wniemiec.mobilang.asc.framework.FrameworkProjectManagerFactory;
import wniemiec.mobilang.asc.models.PropertiesData;

public class ReactNativeFrameworkProjectManagerFactory implements FrameworkProjectManagerFactory {

    @Override
    public FrameworkProjectManager getProjectManager(Path workingDirectory) {
        return new ReactNativeFrameworkProjectManager(workingDirectory);
    }

}

package wniemiec.mobilang.asc.framework.manager.reactnative;

import java.nio.file.Path;

import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManager;
import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManagerFactory;
import wniemiec.mobilang.asc.models.PropertiesData;

public class ReactNativeFrameworkProjectManagerFactory implements FrameworkProjectManagerFactory {

    @Override
    public FrameworkProjectManager getProjectManager(Path workingDirectory) {
        return new ReactNativeFrameworkProjectManager(workingDirectory);
    }

}

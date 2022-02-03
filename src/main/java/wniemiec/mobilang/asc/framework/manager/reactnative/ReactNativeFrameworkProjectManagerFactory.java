package wniemiec.mobilang.asc.framework.manager.reactnative;

import java.nio.file.Path;
import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManager;
import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManagerFactory;


/**
 * Provides project managers of React Native framework.
 */
public class ReactNativeFrameworkProjectManagerFactory implements FrameworkProjectManagerFactory {

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public FrameworkProjectManager getProjectManager(Path workingDirectory) {
        return new ReactNativeFrameworkProjectManager(workingDirectory);
    }
}

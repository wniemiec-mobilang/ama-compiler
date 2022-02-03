package wniemiec.mobilang.asc.framework.manager;

import java.nio.file.Path;


/**
 * Provides project managers of a framework.
 */
public interface FrameworkProjectManagerFactory {

    /**
     * Provides project manager of a framework.
     * 
     * @param       workingDirectory Project directory
     * 
     * @return      Project manager
     */
    FrameworkProjectManager getProjectManager(Path workingDirectory);
}

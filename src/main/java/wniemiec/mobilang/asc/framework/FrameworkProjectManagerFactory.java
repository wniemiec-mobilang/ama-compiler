package wniemiec.mobilang.asc.framework;

import java.nio.file.Path;

import wniemiec.mobilang.asc.models.PropertiesData;

public interface FrameworkProjectManagerFactory {

    FrameworkProjectManager getProjectManager(Path workingDirectory);

}

package wniemiec.mobilang.ama.framework;

import java.io.IOException;
import java.util.List;

import wniemiec.mobilang.ama.coder.exception.CoderException;
import wniemiec.mobilang.ama.models.ProjectCodes;
import wniemiec.mobilang.ama.models.PropertiesData;
import wniemiec.mobilang.ama.models.ScreenData;


/**
 * 
 */
public interface Framework {


    void createProject(PropertiesData propertiesData) throws IOException;

   
    void addProjectDependency(String dependency)  throws IOException;

    ProjectCodes generateCode(List<ScreenData> screensData) throws CoderException;

    //void generateMobileApplications();
}

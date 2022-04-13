package wniemiec.mobilang.asc.framework;

import java.io.IOException;
import java.util.List;

import wniemiec.mobilang.asc.coder.exception.CoderException;
import wniemiec.mobilang.asc.models.ProjectCodes;
import wniemiec.mobilang.asc.models.PropertiesData;
import wniemiec.mobilang.asc.models.ScreenData;


/**
 * 
 */
public interface Framework {


    void createProject(PropertiesData propertiesData) throws IOException;

   
    void addProjectDependency(String dependency)  throws IOException;

    ProjectCodes generateCode(List<ScreenData> screensData) throws CoderException;

    //void generateMobileApplications();
}

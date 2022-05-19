package wniemiec.mobilang.ama.framework.ionic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import wniemiec.mobilang.ama.coder.exception.CoderException;
import wniemiec.mobilang.ama.export.exception.AppGenerationException;
import wniemiec.mobilang.ama.framework.Framework;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.Project;
import wniemiec.mobilang.ama.models.Properties;
import wniemiec.mobilang.ama.models.Screen;


/**
 * Responsible for managing React Native framework.
 * 
 * See: https://ionicframework.com
 */
public class IonicFramework implements Framework {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final IonicProjectManager projectManager;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicFramework() {
        projectManager = new IonicProjectManager();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void createProject(Properties propertiesData, Path location) 
    throws IOException {
        projectManager.createProject(propertiesData, location);
    }

    @Override
    public void addProjectDependency(String dependency, Path location) 
    throws IOException {
        projectManager.addProjectDependency(dependency, location);
    }

    @Override
    public Project generateCode(List<Screen> screensData) 
    throws CoderException {
        List<CodeFile> code = new ArrayList<>();
        Set<String> dependencies = new HashSet<>();
        
        generateScreensCode(code, screensData);
        generateScreensRouting(code, screensData);

        return new Project(code, dependencies);
    }

    private void generateScreensCode(List<CodeFile> code, List<Screen> screensData) 
    throws CoderException {
        IonicScreensCoder screensCoder = new IonicScreensCoder(screensData);

        code.addAll(screensCoder.generateCode());
    }

    private void generateScreensRouting(List<CodeFile> code, List<Screen> screensData) {
        IonicRoutingCoder routingCoder = new IonicRoutingCoder(screensData);

        code.addAll(routingCoder.generateCode());
    }

    @Override
    public void generateMobileApplicationFor(String platform, Path source, Path output) 
    throws AppGenerationException {
        IonicAppGenerator appGenerator = new IonicAppGenerator(source, output);

        appGenerator.generateMobileApplicationFor(platform);
    }
}

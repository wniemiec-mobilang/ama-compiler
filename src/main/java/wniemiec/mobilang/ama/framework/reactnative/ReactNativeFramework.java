package wniemiec.mobilang.ama.framework.reactnative;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import wniemiec.mobilang.ama.coder.exception.CoderException;
import wniemiec.mobilang.ama.framework.Framework;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.ProjectCodes;
import wniemiec.mobilang.ama.models.PropertiesData;
import wniemiec.mobilang.ama.models.ScreenData;


public class ReactNativeFramework implements Framework {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final ReactNativeProjectManager projectManager;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeFramework() {
        projectManager = new ReactNativeProjectManager();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void createProject(PropertiesData propertiesData, Path location) throws IOException {
        projectManager.createProject(propertiesData, location);
    }

    @Override
    public void addProjectDependency(String dependency, Path location) throws IOException {
        projectManager.addProjectDependency(dependency, location);
    }

    @Override
    public ProjectCodes generateCode(List<ScreenData> screensData) throws CoderException {
        List<CodeFile> code = new ArrayList<>();
        Set<String> dependencies = new HashSet<>();
        
        generateCoreCode(code, dependencies);
        generateScreensCode(code, screensData);

        return new ProjectCodes(code, dependencies);
    }

    private void generateCoreCode(List<CodeFile> code, Set<String> dependencies) {
        ReactNativeCoreCoder coreCoder = new ReactNativeCoreCoder();

        code.addAll(coreCoder.generateCode());
        dependencies.addAll(coreCoder.getDependencies());
    }

    private void generateScreensCode(List<CodeFile> code, List<ScreenData> screensData) 
    throws CoderException {
        ReactNativeScreensCoder screensCoder = new ReactNativeScreensCoder(screensData);

        code.addAll(screensCoder.generateCode());
    }

    @Override
    public void generateMobileApplications(Path sourceCode, Path output) 
    throws IOException {
        ReactNativeAppGenerator appGenerator = new ReactNativeAppGenerator(sourceCode, output);

        appGenerator.generateMobileApplications();
    }
}

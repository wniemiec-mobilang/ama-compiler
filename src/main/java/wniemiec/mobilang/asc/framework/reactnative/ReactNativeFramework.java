package wniemiec.mobilang.asc.framework.reactnative;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import wniemiec.mobilang.asc.coder.exception.CoderException;
import wniemiec.mobilang.asc.framework.Framework;
import wniemiec.mobilang.asc.models.CodeFile;
import wniemiec.mobilang.asc.models.ProjectCodes;
import wniemiec.mobilang.asc.models.PropertiesData;
import wniemiec.mobilang.asc.models.ScreenData;


public class ReactNativeFramework implements Framework {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final ReactNativeProjectManager projectManager;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeFramework(Path workingDirectory) {
        projectManager = new ReactNativeProjectManager(workingDirectory);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void createProject(PropertiesData propertiesData) throws IOException {
        projectManager.createProject(propertiesData);
    }

    @Override
    public void addProjectDependency(String dependency) throws IOException {
        projectManager.addProjectDependency(dependency);
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
}

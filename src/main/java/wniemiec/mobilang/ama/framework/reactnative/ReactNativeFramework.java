package wniemiec.mobilang.ama.framework.reactnative;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import wniemiec.mobilang.ama.coder.exception.CoderException;
import wniemiec.mobilang.ama.export.exception.AppGenerationException;
import wniemiec.mobilang.ama.framework.Framework;
import wniemiec.mobilang.ama.framework.reactnative.app.ReactNativeAppGenerator;
import wniemiec.mobilang.ama.framework.reactnative.coder.ReactNativeCoreCoder;
import wniemiec.mobilang.ama.framework.reactnative.coder.ReactNativeScreensCoder;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.Project;
import wniemiec.mobilang.ama.models.Properties;
import wniemiec.mobilang.ama.models.Screen;


/**
 * Responsible for managing React Native framework.
 * 
 * See: https://reactnative.dev
 */
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
        
        generateCoreCode(code, dependencies);
        generateScreensCode(code, screensData);

        return new Project(code, dependencies);
    }

    private void generateCoreCode(List<CodeFile> code, Set<String> dependencies) {
        ReactNativeCoreCoder coreCoder = new ReactNativeCoreCoder();

        code.addAll(coreCoder.generateCode());
        dependencies.addAll(coreCoder.getDependencies());
    }

    private void generateScreensCode(List<CodeFile> code, List<Screen> screensData) 
    throws CoderException {
        ReactNativeScreensCoder screensCoder = new ReactNativeScreensCoder(screensData);

        code.addAll(screensCoder.generateCode());
    }

    @Override
    public void generateMobileApplicationFor(String platform, Path source, Path output) 
    throws AppGenerationException {
        ReactNativeAppGenerator appGenerator = new ReactNativeAppGenerator(source, output);

        appGenerator.generateMobileApplicationFor(platform);
    }
}

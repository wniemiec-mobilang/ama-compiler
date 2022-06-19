package wniemiec.mobilang.ama.framework.reactnative;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.StandardTerminalBuilder;
import wniemiec.io.java.Terminal;
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
import wniemiec.mobilang.ama.util.io.FileManager;
import wniemiec.mobilang.ama.util.io.StandardFileManager;


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
    private final Terminal terminal;
    private final FileManager fileManager;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeFramework() {
        terminal = buildStandardTerminal();
        fileManager = new StandardFileManager();
        projectManager = new ReactNativeProjectManager(terminal, fileManager);
    }

    public ReactNativeFramework(Terminal terminal, FileManager fileManager) {
        projectManager = new ReactNativeProjectManager(terminal, fileManager);
        this.terminal = terminal;
        this.fileManager = fileManager;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Terminal buildStandardTerminal() {
        return StandardTerminalBuilder
            .getInstance()
            .outputHandler(Consolex::writeDebug)
            .outputErrorHandler(Consolex::writeDebug)
            .build();
    }
    
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
        generateCoreCode(code, dependencies);

        return new Project(code, dependencies);
    }
    
    private void generateScreensCode(List<CodeFile> code, List<Screen> screensData) 
    throws CoderException {
        ReactNativeScreensCoder screensCoder = new ReactNativeScreensCoder(screensData);

        code.addAll(screensCoder.generateCode());
    }

    private void generateCoreCode(List<CodeFile> code, Set<String> dependencies) {
        ReactNativeCoreCoder coreCoder = new ReactNativeCoreCoder();

        code.addAll(coreCoder.generateCode());
        dependencies.addAll(coreCoder.getDependencies());
    }

    @Override
    public void generateMobileApplicationFor(String platform, Path source, Path output) 
    throws AppGenerationException {
        ReactNativeAppGenerator appGenerator = new ReactNativeAppGenerator(
            source, 
            output, 
            terminal, 
            fileManager
        );

        appGenerator.generateMobileApplicationFor(platform);
    }
}

package wniemiec.mobilang.ama.framework.ionic;

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
import wniemiec.mobilang.ama.framework.ionic.app.IonicAppGenerator;
import wniemiec.mobilang.ama.framework.ionic.coder.IonicRoutingCoder;
import wniemiec.mobilang.ama.framework.ionic.coder.IonicScreensCoder;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.Project;
import wniemiec.mobilang.ama.models.Properties;
import wniemiec.mobilang.ama.models.Screen;
import wniemiec.mobilang.ama.util.io.StandardFileManager;


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
        projectManager = new IonicProjectManager(
            buildStandardTerminal(), 
            new StandardFileManager()
        );
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
    public void createProject(Properties properties, Path location) 
    throws IOException {
        projectManager.createProject(properties, location);
    }

    @Override
    public void addProjectDependency(String dependency, Path location) 
    throws IOException {
        projectManager.addProjectDependency(dependency, location);
    }

    @Override
    public Project generateCode(List<Screen> screens) 
    throws CoderException {
        List<CodeFile> code = new ArrayList<>();
        Set<String> dependencies = new HashSet<>();
        
        generateScreensCode(code, screens);
        generateScreensRouting(code, screens);

        return new Project(code, dependencies);
    }

    private void generateScreensCode(List<CodeFile> code, List<Screen> screens) 
    throws CoderException {
        IonicScreensCoder screensCoder = new IonicScreensCoder(screens);

        code.addAll(screensCoder.generateCode());
    }

    private void generateScreensRouting(List<CodeFile> code, List<Screen> screens) {
        IonicRoutingCoder routingCoder = new IonicRoutingCoder(screens);

        code.addAll(routingCoder.generateCode());
    }

    @Override
    public void generateMobileApplicationFor(String platform, Path source, Path output) 
    throws AppGenerationException {
        IonicAppGenerator appGenerator = new IonicAppGenerator(source, output);

        appGenerator.generateMobileApplicationFor(platform);
    }
}

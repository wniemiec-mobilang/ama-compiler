package wniemiec.mobilex.ama.framework.ionic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.StandardTerminalBuilder;
import wniemiec.io.java.Terminal;
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.export.exception.AppGenerationException;
import wniemiec.mobilex.ama.framework.Framework;
import wniemiec.mobilex.ama.framework.ionic.app.IonicAppGenerator;
import wniemiec.mobilex.ama.framework.ionic.coder.IonicRoutingCoder;
import wniemiec.mobilex.ama.framework.ionic.coder.IonicScreensCoder;
import wniemiec.mobilex.ama.models.CodeFile;
import wniemiec.mobilex.ama.models.Project;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.models.Screen;
import wniemiec.mobilex.ama.util.io.FileManager;
import wniemiec.mobilex.ama.util.io.StandardFileManager;


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
    private final Terminal terminal;
    private final FileManager fileManager;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicFramework() {
        terminal = buildStandardTerminal();
        fileManager = new StandardFileManager();
        projectManager = new IonicProjectManager(terminal, fileManager);
    }

    public IonicFramework(Terminal terminal, FileManager fileManager) {
        projectManager = new IonicProjectManager(terminal, fileManager);
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
        IonicAppGenerator appGenerator = new IonicAppGenerator(
            source, 
            output, 
            terminal, 
            fileManager
        );

        appGenerator.generateMobileApplicationFor(platform);
    }
}

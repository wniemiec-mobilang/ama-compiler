package wniemiec.mobilex.ama.framework;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.export.exception.AppGenerationException;
import wniemiec.mobilex.ama.models.CodeFile;
import wniemiec.mobilex.ama.models.Project;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.models.Screen;


public class MockFramework implements Framework {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private boolean created;
    private Set<String> dependencies;
    private Set<String> generatedMobileApplications;
    private String lastGeneratedMobileApplication;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public MockFramework() {
        created = false;
        dependencies = new HashSet<>();
        generatedMobileApplications = new HashSet<>();
        lastGeneratedMobileApplication = null;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void createProject(Properties properties, Path location) 
    throws IOException {
        created = true;
    }

    @Override
    public void addProjectDependency(String dependency, Path location) 
    throws IOException {
        dependencies.add(dependency);
    }

    @Override
    public Project generateCode(List<Screen> screens) throws CoderException {
        List<CodeFile> codeFiles = generateCodeFiles(screens);
        
        return new Project(codeFiles, dependencies);
    }

    private List<CodeFile> generateCodeFiles(List<Screen> screens) {
        return screens
            .stream()
            .map(screen -> new CodeFile(screen.getName(), generateScreenCode(screen)))
            .collect(Collectors.toList());
    }

    private List<String> generateScreenCode(Screen screen) {
        List<String> code = new ArrayList<>();

        code.addAll(screen.getStructure().toCode());
        code.addAll(screen.getStyle().toCode());
        code.addAll(screen.getBehavior().toCode());

        return code;
    }

    @Override
    public void generateMobileApplicationFor(String platform, Path source, Path output) 
    throws AppGenerationException {
        lastGeneratedMobileApplication = platform;
        generatedMobileApplications.add(platform);
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public boolean wasProjectCreated() {
        return created;
    }

    public Set<String> getDependencies() {
        return dependencies;
    }

    public String getLastGeneratedMobileApplication() {
        return lastGeneratedMobileApplication;
    }

    public boolean wasGeneratedMobileApplicationFor(String platform) {
        return generatedMobileApplications.contains(platform);
    }
}

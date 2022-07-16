package wniemiec.mobilex.ama.framework.ionic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import wniemiec.io.java.Terminal;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.util.data.Validator;
import wniemiec.mobilex.ama.util.io.FileManager;


/**
 * Responsible for project management of Ionic framework.
 */
class IonicProjectManager {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Terminal terminal;
    private FileManager fileManager;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicProjectManager(Terminal terminal, FileManager fileManager) {
        Validator.validateTerminal(terminal);
        Validator.validateFileManager(fileManager);
        
        this.terminal = terminal;
        this.fileManager = fileManager;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void createProject(Properties properties, Path location) 
    throws IOException {
        Validator.validateProperties(properties);
        Validator.validateLocation(location);

        runIonicInit(properties, location);
        updateGlobalScss(location);
        eraseVariablesScss(location);
        removeHomeFolder(location);
        removeAppRoutingModule(location);
        createPagesFolder(location);
    }

    private void runIonicInit(Properties properties, Path location) 
    throws IOException {
        generateIonicProject(properties);
        moveProjectFolderTo(properties, location);
    }

    private void generateIonicProject(Properties properties) throws IOException {
        terminal.exec(
            "ionic", 
            "start", 
            properties.getApplicationName(),
            "blank",
            "--type=angular",
            "--capacitor",
            "--confirm",
            "--no-interactive"
        );
    }

    private void moveProjectFolderTo(Properties properties, Path location) 
    throws IOException {
        terminal.exec(
            "mv", 
            properties.getApplicationName(), 
            location.getFileName().toString()
        );

        terminal.exec(
            "mv",
            location.getFileName().toString(),
            location.getParent().toString()
        );
    }

    private void updateGlobalScss(Path location) throws IOException {
        Path globalScss = generateGlobalScssPath(location);
        String code = generateScssFixCode();

        appendStringInFile(code, globalScss);
    }

    private Path generateGlobalScssPath(Path location) {
        return location
            .resolve("src")
            .resolve("global.scss");
    }

    private String generateScssFixCode() {
        StringBuilder code = new StringBuilder();

        code.append("global.scss");
        code.append('\n');
        code.append(".ion-page {");
        code.append('\n');
        code.append("  justify-content: flex-start;");
        code.append('\n');
        code.append('}');
        
        return code.toString();
    }

    private void appendStringInFile(String string, Path globalScss) 
    throws IOException {
        fileManager.append(globalScss, List.of(string));
    }

    private void eraseVariablesScss(Path location) throws IOException {
        Path variablesScss = location
            .resolve("src")
            .resolve("theme")
            .resolve("variables.scss");
        
        fileManager.removeFile(variablesScss);
        fileManager.createFile(variablesScss);
    }

    private void removeHomeFolder(Path location) throws IOException {
        Path homeFolderPath = generateHomeFolderPath(location);
        
        fileManager.removeDirectory(homeFolderPath);
    }

    private Path generateHomeFolderPath(Path location) {
        return generateAppPath(location).resolve("home");
    }

    private Path generateAppPath(Path location) {
        return location
            .resolve("src")
            .resolve("app");
    }

    private void removeAppRoutingModule(Path location) throws IOException {
        fileManager.removeFile(generateAppRoutingModulePath(location));
    }

    private Path generateAppRoutingModulePath(Path location) {
        return generateAppPath(location).resolve("app-routing.module.ts");
    }

    private void createPagesFolder(Path location) throws IOException {
        fileManager.createDirectory(generatePagesFolderPath(location));
    }

    private Path generatePagesFolderPath(Path location) {
        return generateAppPath(location).resolve("pages");
    }

    public void addProjectDependency(String dependency, Path projectLocation) 
    throws IOException {
        Validator.validateDependency(dependency);
        Validator.validateLocation(projectLocation);
        
        for (String dependencyName : dependency.split(" ")) {
            terminal.exec(
                "npm", 
                "install", 
                "--prefix",
                projectLocation.toString(),
                "--save", 
                dependencyName
            );
        }
    }
}

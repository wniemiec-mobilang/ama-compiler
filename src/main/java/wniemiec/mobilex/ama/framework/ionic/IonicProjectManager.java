package wniemiec.mobilex.ama.framework.ionic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import wniemiec.io.java.Terminal;
import wniemiec.mobilex.ama.models.Properties;
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
        this.terminal = terminal;
        this.fileManager = fileManager;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void createProject(Properties propertiesData, Path location) 
    throws IOException {
        runIonicInit(propertiesData, location);
        updateGlobalScss(location);
        eraseVariablesScss(location);
        removeHomeFolder(location);
        removeAppRoutingModule(location);
        createPagesFolder(location);
    }

    private void runIonicInit(Properties propertiesData, Path location) 
    throws IOException {
        generateIonicProject(propertiesData);
        moveProjectFolderTo(propertiesData, location);
    }

    private void generateIonicProject(Properties propertiesData) throws IOException {
        terminal.exec(
            "ionic", 
            "start", 
            propertiesData.getAppName(),
            "blank",
            "--type=angular",
            "--capacitor",
            "--confirm",
            "--no-interactive"
        );
    }

    private void moveProjectFolderTo(Properties propertiesData, Path location) 
    throws IOException {
        terminal.exec(
            "mv", 
            propertiesData.getAppName(), 
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

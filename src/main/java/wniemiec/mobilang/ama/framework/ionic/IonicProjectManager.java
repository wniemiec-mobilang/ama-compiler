package wniemiec.mobilang.ama.framework.ionic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.apache.commons.io.FileUtils;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.StandardTerminalBuilder;
import wniemiec.io.java.Terminal;
import wniemiec.mobilang.ama.models.PropertiesData;


/**
 * Responsible for project management of Ionic framework.
 */
class IonicProjectManager {

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void createProject(PropertiesData propertiesData, Path location) 
    throws IOException {
        runIonicInit(propertiesData, location);
        updateGlobalScss(location);
        removeHomeFolder(location);
        removeAppRoutingModule(location);
        createPagesFolder(location);
    }

    private void runIonicInit(PropertiesData propertiesData, Path location) 
    throws IOException {
        generateIonicProject(propertiesData);
        moveProjectFolderTo(propertiesData, location);
    }

    private void generateIonicProject(PropertiesData propertiesData) throws IOException {
        exec(
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

    private void moveProjectFolderTo(PropertiesData propertiesData, Path location) 
    throws IOException {
        exec(
            "mv", 
            propertiesData.getAppName(), 
            location.getFileName().toString()
        );

        exec(
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

        code.append("// FIX HTML FLEX CSS");
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
        Files.write(
            globalScss, 
            string.getBytes(), 
            StandardOpenOption.APPEND
        );
    }

    private void removeHomeFolder(Path location) throws IOException {
        Path homeFolderPath = generateHomeFolderPath(location);
        
        FileUtils.deleteDirectory(homeFolderPath.toFile());
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
        Files.delete(generateAppRoutingModulePath(location));
    }

    private Path generateAppRoutingModulePath(Path location) {
        return generateAppPath(location).resolve("app-routing.module.ts");
    }

    private void createPagesFolder(Path location) throws IOException {
        Files.createDirectory(generatePagesFolderPath(location));
    }

    private Path generatePagesFolderPath(Path location) {
        return generateAppPath(location).resolve("pages");
    }

    private void exec(String... command) throws IOException {
        Terminal terminal = StandardTerminalBuilder
            .getInstance()
            .outputHandler(Consolex::writeDebug)
            .outputErrorHandler(Consolex::writeDebug)
            .build();
        
        terminal.exec(command);
    }

    public void addProjectDependency(String dependency, Path projectLocation) 
    throws IOException {
        for (String dependencyName : dependency.split(" ")) {
            exec(
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

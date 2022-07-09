package wniemiec.mobilex.ama.export;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.TextFileManager;
import wniemiec.mobilex.ama.export.exception.CodeExportException;
import wniemiec.mobilex.ama.framework.Framework;
import wniemiec.mobilex.ama.models.CodeFile;
import wniemiec.mobilex.ama.models.Properties;


/**
 * Responsible for Mobilang code exportation.
 */
public class MobilangCodeExport {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Properties propertiesData;
    private final Set<String> dependencies;
    private final Path outputLocation;
    private final Path codeLocation;
    private final Framework framework;
    private final List<CodeFile> codeFiles;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Mobilang code exportation.
     * 
     * @param       propertiesData Properties data
     * @param       codeFiles Code files
     * @param       dependencies Project dependencies
     * @param       framework Framework that will handle with project management
     * @param       outputLocation Location where the files will be exported
     * 
     * @throws      CodeExportException If output location cannot be reached
     */
    public MobilangCodeExport(
        Properties propertiesData,
        List<CodeFile> codeFiles,
        Set<String> dependencies,
        Framework framework, 
        Path outputLocation
    ) throws CodeExportException {
        this.propertiesData = propertiesData;
        this.codeFiles = codeFiles;
        this.dependencies = dependencies;
        this.framework = framework;
        this.outputLocation = outputLocation;
        codeLocation = setUpAppLocation(propertiesData, outputLocation);
        setUpOutputLocation();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Path setUpAppLocation(Properties propertiesData, Path outputLocation) {
        if (outputLocation == null) {
            return Path.of(propertiesData.getApplicationName()).resolve("code");
        }
        
        return outputLocation.resolve(propertiesData.getApplicationName()).resolve("code");
    }

    private void setUpOutputLocation() throws CodeExportException {
        try {
            cleanOutputLocation(outputLocation);
        } 
        catch (IOException e) {
            throw new CodeExportException(e.getMessage());
        }
    }

    private void cleanOutputLocation(Path outputLocation) throws IOException {
        if (!Files.exists(outputLocation)) {
            Files.createDirectories(outputLocation);
        }

        FileUtils.deleteDirectory(codeLocation.toFile());
        Files.createDirectories(codeLocation);
    }

    public Path export() throws CodeExportException {
        createProject();
        exportCode();

        return codeLocation;
    }

    public void createProject() throws CodeExportException {
        try {
            framework.createProject(propertiesData, codeLocation);

            for (String dependency : dependencies) {
                framework.addProjectDependency(dependency, codeLocation);
            }
        } 
        catch (IOException e) {
            throw new CodeExportException(e.getMessage());
        }
    }

    private void exportCode() throws CodeExportException {
        for (CodeFile file : codeFiles) {
            exportCodeFile(file.getName(), file.getCode());
        }
    }

    private void exportCodeFile(String filename, List<String> code) 
    throws CodeExportException {
        Path filepath = buildFilepath(filename);
        
        Consolex.writeDebug("Exporting " + filepath);
        
        writeCodeLines(code, filepath);
    }

    private Path buildFilepath(String filename) {
        return codeLocation.resolve(filename);
    }

    private void writeCodeLines(List<String> code, Path filepath) 
    throws CodeExportException {
        try {
            writeLines(code, filepath);
        } 
        catch (IOException e) {
            throw new CodeExportException(e.getMessage());
        }
    }

    private void writeLines(List<String> lines, Path filepath) throws IOException {
        TextFileManager txtFileManager = new TextFileManager(
            filepath, 
            StandardCharsets.UTF_8
        );

        txtFileManager.writeLines(lines);
    }
}

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
    private final Properties properties;
    private final Set<String> dependencies;
    private final Path output;
    private final Path codeLocation;
    private final Framework framework;
    private final List<CodeFile> codeFiles;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Mobilang code exportation.
     * 
     * @param       properties Properties data
     * @param       codeFiles Code files
     * @param       dependencies Project dependencies
     * @param       framework Framework that will handle with project management
     * @param       output Location where the files will be exported
     * 
     * @throws      CodeExportException If output location cannot be reached
     */
    public MobilangCodeExport(
        Properties properties,
        List<CodeFile> codeFiles,
        Set<String> dependencies,
        Framework framework, 
        Path output
    ) throws CodeExportException {
        this.properties = properties;
        this.codeFiles = codeFiles;
        this.dependencies = dependencies;
        this.framework = framework;
        this.output = output;
        codeLocation = setUpAppLocation(properties, output);
        setUpOutputLocation();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Path setUpAppLocation(Properties properties, Path output) {
        if (output == null) {
            return Path.of(properties.getApplicationName()).resolve("code");
        }
        
        return output.resolve(properties.getApplicationName()).resolve("code");
    }

    private void setUpOutputLocation() throws CodeExportException {
        try {
            cleanOutputLocation(output);
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

    private void createProject() throws CodeExportException {
        try {
            framework.createProject(properties, codeLocation);

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

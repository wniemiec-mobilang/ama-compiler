package wniemiec.mobilang.ama.export;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.TextFileManager;
import wniemiec.mobilang.ama.export.exception.CodeExportException;
import wniemiec.mobilang.ama.export.exception.OutputLocationException;
import wniemiec.mobilang.ama.framework.Framework;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.PropertiesData;


/**
 * Responsible for exporting MobiLang code to files.
 */
public class FileMobiLangCodeExport extends MobiLangCodeExport {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Framework framework;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Exports MobiLang code to files.
     * 
     * @param       propertiesData Properties data
     * @param       codeFiles Code files
     * @param       dependencies Project dependencies
     * @param       framework Framework that will handle with project management
     * @param       outputLocation Location where the files will be exported
     * 
     * @throws      OutputLocationException If output location cannot be reached
     */
    public FileMobiLangCodeExport(
        PropertiesData propertiesData, 
        List<CodeFile> codeFiles, 
        Set<String> dependencies,
        Framework framework, 
        Path outputLocation
    ) throws OutputLocationException {
        super(propertiesData, codeFiles, dependencies, outputLocation);
        setUpOutputLocation();

        this.framework = framework;
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void setUpOutputLocation() 
    throws OutputLocationException {
        try {
            cleanOutputLocation(outputLocation);
        } 
        catch (IOException e) {
            throw new OutputLocationException(e.getMessage());
        }
    }

    private void cleanOutputLocation(Path outputLocation) throws IOException {
        if (!Files.exists(outputLocation)) {
            Files.createDirectories(outputLocation);
        }

        FileUtils.deleteDirectory(codeLocation.toFile());
        Files.createDirectories(codeLocation);
    }

    @Override
    protected void exportCodeFile(String filename, List<String> code) 
    throws CodeExportException {
        Path filepath = buildFilepath(filename);
        
        Consolex.writeInfo("Exporting " + filepath);
        
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

    @Override
    public void createProject() throws CodeExportException {
        try {
            framework.createProject(propertiesData);

            for (String dependency : dependencies) {
                framework.addProjectDependency(dependency);
            }
        } 
        catch (IOException e) {
            throw new CodeExportException(e.getMessage());
        }
    }   
}

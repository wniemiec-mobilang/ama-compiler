package wniemiec.mobilang.asc.export;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.TextFileManager;
import wniemiec.mobilang.asc.export.exception.CodeExportException;
import wniemiec.mobilang.asc.export.exception.OutputLocationException;
import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManager;
import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManagerFactory;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PropertiesData;


/**
 * Responsible for exporting MobiLang code to files.
 */
public class FileMobiLangCodeExport extends MobiLangCodeExport {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Path appLocation;
    private FrameworkProjectManager projectManager;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Exports MobiLang code to files.
     * 
     * @param       propertiesData Properties data
     * @param       screensCode Screens code
     * @param       persistenceCode Persistence code
     * @param       coreCode Core code
     * @param       dependencies Project dependencies
     * @param       frameworkProjectManagerFactory Factory that will provide
     * framework project management
     * @param       outputLocation Location where the files will be exported
     * 
     * @throws      OutputLocationException If output location cannot be reached
     */
    public FileMobiLangCodeExport(
        PropertiesData propertiesData, 
        List<FileCode> screensCode, 
        List<FileCode> persistenceCode,
        List<FileCode> coreCode,
        Set<String> dependencies,
        FrameworkProjectManagerFactory frameworkProjectManagerFactory, 
        Path outputLocation
    ) throws OutputLocationException {
        super(propertiesData, screensCode, persistenceCode, coreCode, dependencies);
        
        setUpAppLocation(propertiesData, outputLocation);
        setUpProjectManager(frameworkProjectManagerFactory, outputLocation);
        setUpOutputLocation(outputLocation);
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void setUpAppLocation(PropertiesData propertiesData, Path outputLocation) {
        appLocation = outputLocation.resolve(propertiesData.getAppName());
    }

    private void setUpProjectManager(
        FrameworkProjectManagerFactory projectManagerFactory,
        Path outputLocation
    ) {
        projectManager = projectManagerFactory.getProjectManager(outputLocation);
    }

    private void setUpOutputLocation(Path outputLocation) 
    throws OutputLocationException {
        try {
            cleanOutputLocation(outputLocation);
        } 
        catch (IOException e) {
            throw new OutputLocationException(e.getMessage());
        }
    }

    private void cleanOutputLocation(Path outputLocation) throws IOException {
        FileUtils.deleteDirectory(outputLocation.toFile());
        Files.createDirectories(outputLocation);
    }

    @Override
    protected void exportScreenCode(String filename, List<String> code) 
    throws CodeExportException {
        exportCodeToFile(filename, code);
    }

    private void exportCodeToFile(String filename, List<String> code) 
    throws CodeExportException {
        Path filepath = buildFilepath(filename);
        
        Consolex.writeInfo("Exporting " + filepath);
        
        writeLines(code, filepath);
    }

    private Path buildFilepath(String filename) {
        return appLocation.resolve(filename);
    }

    private void writeLines(List<String> code, Path filepath) 
    throws CodeExportException {
        TextFileManager txtFileManager = new TextFileManager(
            filepath, 
            StandardCharsets.ISO_8859_1
        );
        
        try {
            txtFileManager.writeLines(code);
        } 
        catch (IOException e) {
            throw new CodeExportException(e.getMessage());
        }
    }
    
    @Override
    protected void exportCoreCode(String filename, List<String> code) 
    throws CodeExportException {
        exportCodeToFile(filename, code);
    }

    @Override
    protected void exportPersistenceCode(String filename, List<String> code) 
    throws CodeExportException {
        exportCodeToFile(filename, code);
    }

    @Override
    public void createProject() throws CodeExportException {
        try {
            projectManager.create(propertiesData);

            for (String dependency : dependencies) {
                projectManager.addDependency(dependency);
            }
        } 
        catch (IOException e) {
            throw new CodeExportException(e.getMessage());
        }
    }   
}

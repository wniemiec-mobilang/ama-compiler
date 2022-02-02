package wniemiec.mobilang.asc.export;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import wniemiec.io.java.Consolex;
import wniemiec.io.java.TextFileManager;
import wniemiec.mobilang.asc.export.exception.CodeExportException;
import wniemiec.mobilang.asc.export.exception.OutputLocationException;
import wniemiec.mobilang.asc.framework.FrameworkProjectManager;
import wniemiec.mobilang.asc.framework.FrameworkProjectManagerFactory;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PropertiesData;
import wniemiec.mobilang.asc.utils.Shell;

public class FileMobilangCodeExport extends MobilangCodeExport {

    private Path outputLocation;
    private FrameworkProjectManagerFactory frameworkProjectManagerFactory;

    public FileMobilangCodeExport(
        PropertiesData propertiesData, 
        List<FileCode> screensCode, 
        List<FileCode> persistenceCode,
        List<FileCode> coreCode,
        FrameworkProjectManagerFactory frameworkProjectManagerFactory, 
        Path outputLocation
    ) throws OutputLocationException {
        super(propertiesData, screensCode, persistenceCode, coreCode);
        this.frameworkProjectManagerFactory = frameworkProjectManagerFactory;
        this.outputLocation = outputLocation;

        System.out.println("@@ " + outputLocation);
        try {
            FileUtils.deleteDirectory(outputLocation.toFile());
            Files.createDirectories(outputLocation);
        } 
        catch (IOException e) {
            throw new OutputLocationException(e.getMessage());
        }
    }

    

    @Override
    protected void exportScreenCode(String filename, List<String> code) throws CodeExportException {
        exportCodeToFile(filename, code);
    }

    private void exportCodeToFile(String filename, List<String> code) throws CodeExportException {
        Path filepath = outputLocation.resolve(Path.of(propertiesData.getName(), filename));
        TextFileManager txtFileManager = new TextFileManager(filepath, StandardCharsets.ISO_8859_1);
        
        System.out.println("Exporting " + filepath);
        
        try {
            txtFileManager.writeLines(code);
        } 
        catch (IOException e) {
            throw new CodeExportException(e.getMessage());
        }
    }

    @Override
    protected void exportCoreCode(String filename, List<String> code) throws CodeExportException {
        exportCodeToFile(filename, code);
    }



    @Override
    public void createProject() throws CodeExportException {
        FrameworkProjectManager projectManager = frameworkProjectManagerFactory.getProjectManager(outputLocation);
        try {
            projectManager.create(propertiesData);
        } 
        catch (IOException e) {
            throw new CodeExportException(e.getMessage());
        }
    }
}

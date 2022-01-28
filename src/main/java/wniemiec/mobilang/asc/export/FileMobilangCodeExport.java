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
import wniemiec.mobilang.asc.models.PropertiesData;
import wniemiec.mobilang.asc.utils.Shell;

public class FileMobilangCodeExport extends MobilangCodeExport {

    private Path outputLocation;

    public FileMobilangCodeExport(
        PropertiesData propertiesData, 
        Map<String, List<String>> screensCode, 
        Map<String, List<String>> persistenceCode,
        Map<String, List<String>> coreCode,
        Path outputLocation
    ) throws OutputLocationException {
        super(propertiesData, screensCode, persistenceCode, coreCode);
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
        Path filepath = outputLocation.resolve(filename);
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
    public void createProject(PropertiesData propertiesData) {
        Shell shell = new Shell();
        //shell.exec("cd " + outputLocation.toString(), "react-native init " + appName);
        
        if (shell.hasError()) {
            Consolex.writeError(shell.getErrorOutput());
        }
        else {
            Consolex.writeInfo(shell.getOutput());
        }
    }
}

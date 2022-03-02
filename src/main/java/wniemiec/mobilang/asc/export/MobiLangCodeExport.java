package wniemiec.mobilang.asc.export;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import wniemiec.mobilang.asc.export.exception.CodeExportException;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PropertiesData;


/**
 * Responsible for MobiLang code exportation.
 */
public abstract class MobiLangCodeExport {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    protected final PropertiesData propertiesData;
    protected final Set<String> dependencies;
    protected final Path outputLocation;
    protected final Path codeLocation;
    private final List<FileCode> screensCode;
    private final List<FileCode> persistenceCode;
    private final List<FileCode> coreCode;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * MobiLang code exportation.
     * 
     * @param       propertiesData Properties data
     * @param       screensCode Screens code
     * @param       persistenceCode Persistence code
     * @param       coreCode Core code
     * @param       dependencies Project dependencies
     * @param       outputLocation Location where the files will be exported
     */
    protected MobiLangCodeExport(
        PropertiesData propertiesData,
        List<FileCode> screensCode,
        List<FileCode> persistenceCode,
        List<FileCode> coreCode,
        Set<String> dependencies,
        Path outputLocation
    ) {
        this.propertiesData = propertiesData;
        this.screensCode = screensCode;
        this.persistenceCode = persistenceCode;
        this.coreCode = coreCode;
        this.dependencies = dependencies;
        this.outputLocation = outputLocation;
        codeLocation = setUpAppLocation(propertiesData, outputLocation);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Path setUpAppLocation(PropertiesData propertiesData, Path outputLocation) {
        return outputLocation.resolve(propertiesData.getAppName()).resolve("code");
    }

    public final Path export() throws CodeExportException {
        createProject();
        exportScreensCode();
        exportCoreCode();
        exportPersistenceCode();

        return codeLocation;
    }

    protected abstract void createProject() throws CodeExportException;

    private void exportScreensCode() throws CodeExportException {
        for (FileCode fileCode : screensCode) {
            exportScreenCode(fileCode.getName(), fileCode.getCode());
        }
    }

    protected abstract void exportScreenCode(String filename, List<String> code)
    throws CodeExportException;

    private void exportCoreCode() throws CodeExportException {
        for (FileCode fileCode : coreCode) {
            exportCoreCode(fileCode.getName(), fileCode.getCode());
        }
    }

    protected abstract void exportCoreCode(String filename, List<String> code)
    throws CodeExportException;

    private void exportPersistenceCode() throws CodeExportException {
        for (FileCode fileCode : persistenceCode) {
            exportPersistenceCode(fileCode.getName(), fileCode.getCode());
        }
    }

    protected abstract void exportPersistenceCode(String filename, List<String> code)
    throws CodeExportException;
}

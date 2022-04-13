package wniemiec.mobilang.asc.export;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import wniemiec.mobilang.asc.export.exception.CodeExportException;
import wniemiec.mobilang.asc.models.CodeFile;
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
    private final List<CodeFile> codeFiles;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * MobiLang code exportation.
     * 
     * @param       propertiesData Properties data
     * @param       codeFiles Code files
     * @param       dependencies Project dependencies
     * @param       outputLocation Location where the files will be exported
     */
    protected MobiLangCodeExport(
        PropertiesData propertiesData,
        List<CodeFile> codeFiles,
        Set<String> dependencies,
        Path outputLocation
    ) {
        this.propertiesData = propertiesData;
        this.codeFiles = codeFiles;
        this.dependencies = dependencies;
        this.outputLocation = outputLocation;
        codeLocation = setUpAppLocation(propertiesData, outputLocation);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Path setUpAppLocation(PropertiesData propertiesData, Path outputLocation) {
        if (outputLocation == null) {
            return Path.of(propertiesData.getAppName()).resolve("code");
        }
        
        return outputLocation.resolve(propertiesData.getAppName()).resolve("code");
    }

    public final Path export() throws CodeExportException {
        createProject();
        exportCode();

        return codeLocation;
    }

    protected abstract void createProject() throws CodeExportException;

    private void exportCode() throws CodeExportException {
        for (CodeFile file : codeFiles) {
            exportCodeFile(file.getName(), file.getCode());
        }
    }

    protected abstract void exportCodeFile(String filename, List<String> code)
    throws CodeExportException;
}

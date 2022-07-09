package wniemiec.mobilex.ama.export.code;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import wniemiec.mobilex.ama.export.exception.CodeExportException;
import wniemiec.mobilex.ama.models.CodeFile;
import wniemiec.mobilex.ama.models.Properties;


/**
 * Responsible for MobiLang code exportation.
 */
public abstract class MobiLangCodeExport {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    protected final Properties propertiesData;
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
        Properties propertiesData,
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
    private Path setUpAppLocation(Properties propertiesData, Path outputLocation) {
        if (outputLocation == null) {
            return Path.of(propertiesData.getApplicationName()).resolve("code");
        }
        
        return outputLocation.resolve(propertiesData.getApplicationName()).resolve("code");
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

package wniemiec.mobilang.asc.export;

import java.util.List;
import java.util.Map;

import wniemiec.mobilang.asc.export.exception.CodeExportException;
import wniemiec.mobilang.asc.framework.FrameworkProjectManager;
import wniemiec.mobilang.asc.models.PropertiesData;

public abstract class MobilangCodeExport {

    protected PropertiesData propertiesData;
    private Map<String, List<String>> screensCode;
    private Map<String, List<String>> persistenceCode;
    private Map<String, List<String>> coreCode;
    
    protected MobilangCodeExport(
        PropertiesData propertiesData,
        Map<String, List<String>> screensCode,
        Map<String, List<String>> persistenceCode,
        Map<String, List<String>> coreCode
    ) {
        this.propertiesData = propertiesData;
        this.screensCode = screensCode;
        this.persistenceCode = persistenceCode;
        this.coreCode = coreCode;
    }

    public final void export() throws CodeExportException {
        createProject();
        exportScreensCode();
        exportCoreCode();
    }

    protected abstract void createProject() throws CodeExportException;

    private void exportScreensCode() throws CodeExportException {
        for (Map.Entry<String, List<String>> screen : screensCode.entrySet()) {
            exportScreenCode(screen.getKey(), screen.getValue());
        }
    }

    protected abstract void exportScreenCode(String filename, List<String> code)
    throws CodeExportException;

    private void exportCoreCode() throws CodeExportException {
        for (Map.Entry<String, List<String>> core : coreCode.entrySet()) {
            exportCoreCode(core.getKey(), core.getValue());
        }
    }

    protected abstract void exportCoreCode(String filename, List<String> code)
    throws CodeExportException;
}

package wniemiec.mobilang.asc.export;

import java.util.List;
import java.util.Map;

import wniemiec.mobilang.asc.export.exception.CodeExportException;
import wniemiec.mobilang.asc.framework.FrameworkProjectManager;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PropertiesData;

public abstract class MobilangCodeExport {

    protected PropertiesData propertiesData;
    private List<FileCode> screensCode;
    private List<FileCode> persistenceCode;
    private List<FileCode> coreCode;
    
    protected MobilangCodeExport(
        PropertiesData propertiesData,
        List<FileCode> screensCode,
        List<FileCode> persistenceCode,
        List<FileCode> coreCode
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
}

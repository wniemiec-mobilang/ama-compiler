package wniemiec.mobilang.asc.export;

import java.util.List;
import java.util.Map;

import wniemiec.mobilang.asc.export.exception.CodeExportException;

public abstract class MobilangCodeExport {

    private Map<String, List<String>> screensCode;
    private Map<String, List<String>> persistenceCode;
    private Map<String, List<String>> coreCode;
    
    protected MobilangCodeExport(
        Map<String, List<String>> screensCode,
        Map<String, List<String>> persistenceCode,
        Map<String, List<String>> coreCode
    ) {
        this.screensCode = screensCode;
        this.persistenceCode = persistenceCode;
        this.coreCode = coreCode;
    }

    public final void export() throws CodeExportException {
        exportScreensCode();
        exportCoreCode();
    }

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

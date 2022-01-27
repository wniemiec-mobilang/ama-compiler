package wniemiec.mobilang.asc.export;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileMobilangCodeExport extends MobilangCodeExport {

    private Path outputLocation;

    public FileMobilangCodeExport(
        Map<String, List<String>> screensCode, 
        Map<String, List<String>> persistenceCode,
        Map<String, List<String>> coreCode,
        Path outputLocation
    ) {
        super(screensCode, persistenceCode, coreCode);
        this.outputLocation = outputLocation;

        System.out.println("@@ " + outputLocation);
    }

    @Override
    protected void exportScreenCode(String filename, List<String> code) {
        // TODO Auto-generated method stub
        System.out.println("Exporting " + filename);   
    }

    @Override
    protected void exportCoreCode(String filename, List<String> code) {
        // TODO Auto-generated method stub
        System.out.println("Exporting " + filename);   
    }
}

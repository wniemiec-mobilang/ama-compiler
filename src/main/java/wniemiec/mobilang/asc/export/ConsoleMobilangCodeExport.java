package wniemiec.mobilang.asc.export;

import java.util.List;
import java.util.Map;

import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PropertiesData;

public class ConsoleMobilangCodeExport extends MobilangCodeExport {

    public ConsoleMobilangCodeExport(
        PropertiesData propertiesData, 
        List<FileCode> screensCode,
        List<FileCode> persistenceCode, 
        List<FileCode> coreCode
    ) {
        super(propertiesData, screensCode, persistenceCode, coreCode);
    }

    @Override
    protected void exportScreenCode(String filename, List<String> code) {
        System.out.println("-----< " + filename + " >-----");
        for (String line : code) {
            System.out.println(line);
        }
    }

    @Override
    protected void exportCoreCode(String filename, List<String> code) {
        System.out.println("-----< " + filename + " >-----");
        for (String line : code) {
            System.out.println(line);
        }
    }

    @Override
    public void createProject() {
        System.out.println("Creating project " + propertiesData.getName());
    }
}

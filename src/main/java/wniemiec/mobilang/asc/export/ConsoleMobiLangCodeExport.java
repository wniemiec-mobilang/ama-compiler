package wniemiec.mobilang.asc.export;

import java.util.List;
import java.util.Set;

import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PropertiesData;


/**
 * Responsible for exporting MobiLang code to console.
 */
public class ConsoleMobiLangCodeExport extends MobiLangCodeExport {

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Exports MobiLang code to console.
     * 
     * @param       propertiesData Properties data
     * @param       screensCode Screens code
     * @param       persistenceCode Persistence code
     * @param       coreCode Core code
     * @param       dependencies Project dependencies
     */
    public ConsoleMobiLangCodeExport(
        PropertiesData propertiesData, 
        List<FileCode> screensCode,
        List<FileCode> persistenceCode, 
        List<FileCode> coreCode,
        Set<String> dependencies
    ) {
        super(propertiesData, screensCode, persistenceCode, coreCode, dependencies);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    protected void exportScreenCode(String filename, List<String> code) {
        printCode(filename, code);
    }

    private void printCode(String filename, List<String> code) {
        Consolex.writeHeader(filename);
        
        for (String line : code) {
            Consolex.writeLine(line);
        }
    }

    @Override
    protected void exportCoreCode(String filename, List<String> code) {
        printCode(filename, code);
    }

    @Override
    protected void exportPersistenceCode(String filename, List<String> code) {
        printCode(filename, code);
    }

    @Override
    public void createProject() {
        Consolex.writeLine("Creating project " + propertiesData.getAppName());

        for (String dependency : dependencies) {
            Consolex.writeLine("Installing dependency " + dependency);
        }
    }
}

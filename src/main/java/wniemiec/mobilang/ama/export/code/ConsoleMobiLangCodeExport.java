package wniemiec.mobilang.ama.export.code;

import java.util.List;
import java.util.Set;
import wniemiec.io.java.Consolex;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.PropertiesData;


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
     * @param       codeFiles Code files
     * @param       persistenceCode Persistence code
     * @param       coreCode Core code
     * @param       dependencies Project dependencies
     */
    public ConsoleMobiLangCodeExport(
        PropertiesData propertiesData, 
        List<CodeFile> codeFiles,
        Set<String> dependencies
    ) {
        super(propertiesData, codeFiles, dependencies, null);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    protected void exportCodeFile(String filename, List<String> code) {
        printCode(filename, code);
    }

    private void printCode(String filename, List<String> code) {
        Consolex.writeHeader(filename);
        
        for (String line : code) {
            Consolex.writeLine(line);
        }
    }

    @Override
    public void createProject() {
        Consolex.writeLine("Creating project " + propertiesData.getAppName());

        for (String dependency : dependencies) {
            Consolex.writeLine("Installing dependency " + dependency);
        }
    }
}

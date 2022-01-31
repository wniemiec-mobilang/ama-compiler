package wniemiec.mobilang.asc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedMap;
import wniemiec.mobilang.asc.coder.MobilangCoder;
import wniemiec.mobilang.asc.export.FileMobilangCodeExport;
import wniemiec.mobilang.asc.export.MobilangCodeExport;
import wniemiec.mobilang.asc.export.exception.CodeExportException;
import wniemiec.mobilang.asc.export.exception.OutputLocationException;
import wniemiec.mobilang.asc.framework.FrameworkFactory;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.parser.MobiLangAstParser;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.reader.DotReader;


/**
 * Responsible for managing ASC compiler pipeline.
 */
public class Asc {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Path dotFilePath;
    private Path outputLocationPath;
    private FrameworkFactory frameworkFactory;
    private SortedMap<String, List<Node>> ast;
    private MobiLangAstParser mobilangAstParser;
    private MobilangCoder mobilangCoder;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Manager for ASC compiler pipeline.
     * 
     * @param       dotFilePath MobiLang dot file
     * @param       outputLocationPath Path where compiler output will be put
     * @param       frameworkFactory Factory that will provide framework service
     */
    public Asc(Path dotFilePath, Path outputLocationPath, FrameworkFactory frameworkFactory) {
        this.dotFilePath = dotFilePath;
        this.outputLocationPath = outputLocationPath;
        this.frameworkFactory = frameworkFactory;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void run() 
    throws ParseException, OutputLocationException, CodeExportException, IOException {
        readMobilangDotFile();
        parseMobilangAst();
        generateMobilangCode();
        exportMobilangCode();
    }

    private void readMobilangDotFile() throws FileNotFoundException {
        DotReader dotReader = new DotReader();
        
        dotReader.read(dotFilePath);
        
        ast = dotReader.getTree();
    }

    private void parseMobilangAst() throws ParseException, IOException {
        mobilangAstParser = new MobiLangAstParser(
            ast, 
            frameworkFactory.getParserFactory()
        );
        mobilangAstParser.parse();
    }

    private void generateMobilangCode() {
        mobilangCoder = new MobilangCoder(
            mobilangAstParser.getPersistenceData(),
            mobilangAstParser.getScreensData(),
            frameworkFactory.getCoderFactory()
        );
        mobilangCoder.generateCode();
    }

    private void exportMobilangCode() 
    throws OutputLocationException, CodeExportException {
        MobilangCodeExport mobilangCodeExport = new FileMobilangCodeExport(
            mobilangAstParser.getPropertiesData(),
            mobilangCoder.getScreensCode(),
            mobilangCoder.getPersistenceCode(),
            mobilangCoder.getCoreCode(),
            frameworkFactory.getProjectManagerFactory(),
            outputLocationPath
        );
        mobilangCodeExport.export();
    }
}

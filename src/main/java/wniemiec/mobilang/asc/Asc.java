package wniemiec.mobilang.asc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedMap;

import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.coder.MobiLangCoder;
import wniemiec.mobilang.asc.export.FileMobiLangCodeExport;
import wniemiec.mobilang.asc.export.MobiLangCodeExport;
import wniemiec.mobilang.asc.export.exception.CodeExportException;
import wniemiec.mobilang.asc.export.exception.OutputLocationException;
import wniemiec.mobilang.asc.framework.FrameworkFactory;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.parser.MobiLangAstParser;
import wniemiec.mobilang.asc.parser.exception.FactoryException;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.reader.DotReader;


/**
 * Responsible for managing ASC compiler pipeline.
 */
public class Asc {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Path mobilangAstFilePath;
    private final Path outputLocationPath;
    private final FrameworkFactory frameworkFactory;
    private SortedMap<String, List<Node>> ast;
    private MobiLangAstParser mobilangAstParser;
    private MobiLangCoder mobilangCoder;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Manager for ASC compiler pipeline.
     * 
     * @param       mobilangAstFilePath MobiLang AST dot file
     * @param       outputLocationPath Path where compiler output will be put
     * @param       frameworkName Framework to be used
     * @throws FactoryException
     */
    public Asc(Path mobilangAstFilePath, Path outputLocationPath, String frameworkName) 
    throws FactoryException {
        this.mobilangAstFilePath = mobilangAstFilePath;
        this.outputLocationPath = outputLocationPath;
        this.frameworkFactory = FrameworkFactory.getInstance(frameworkName);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Path run() 
    throws ParseException, OutputLocationException, CodeExportException, IOException {
        readMobilangDotFile();
        parseMobilangAst();
        generateMobilangCode();
        
        return exportMobilangCode();
    }

    private void readMobilangDotFile() throws FileNotFoundException {
        DotReader dotReader = new DotReader();
        
        Consolex.writeInfo("Reading MobiLang AST...");
        dotReader.read(mobilangAstFilePath);
        
        ast = dotReader.getTree();
    }

    private void parseMobilangAst() throws ParseException, IOException {
        mobilangAstParser = new MobiLangAstParser(
            ast, 
            frameworkFactory.getParserFactory()
        );

        Consolex.writeInfo("Parsing MobiLang AST...");
        mobilangAstParser.parse();
    }

    private void generateMobilangCode() {
        mobilangCoder = new MobiLangCoder(
            mobilangAstParser.getPersistenceData(),
            mobilangAstParser.getScreensData(),
            frameworkFactory.getCoderFactory()
        );
        mobilangCoder.generateCode();
    }

    private Path exportMobilangCode() 
    throws OutputLocationException, CodeExportException {
        MobiLangCodeExport mobilangCodeExport = new FileMobiLangCodeExport(
            mobilangAstParser.getPropertiesData(),
            mobilangCoder.getScreensCode(),
            mobilangCoder.getPersistenceCode(),
            mobilangCoder.getCoreCode(),
            mobilangCoder.getDependencies(),
            frameworkFactory.getProjectManagerFactory(),
            outputLocationPath
        );
        
        Consolex.writeInfo("Exporting code...");

        return mobilangCodeExport.export();
    }
}

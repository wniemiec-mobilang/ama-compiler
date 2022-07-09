package wniemiec.mobilex.ama;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedMap;
import wniemiec.io.java.Consolex;
import wniemiec.mobilex.ama.coder.MobiLangCoder;
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.export.app.MobiLangAppExport;
import wniemiec.mobilex.ama.export.code.FileMobiLangCodeExport;
import wniemiec.mobilex.ama.export.code.MobiLangCodeExport;
import wniemiec.mobilex.ama.export.exception.AppGenerationException;
import wniemiec.mobilex.ama.export.exception.CodeExportException;
import wniemiec.mobilex.ama.framework.Framework;
import wniemiec.mobilex.ama.framework.FrameworkFactory;
import wniemiec.mobilex.ama.models.Node;
import wniemiec.mobilex.ama.parser.MobilangAstParser;
import wniemiec.mobilex.ama.parser.exception.FactoryException;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.reader.MobilangDotReader;


/**
 * Responsible for managing Abstract Syntax Tree to Mobile Application (AMA) 
 * compiler pipeline.
 */
public class Ama {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Path mobilangAstFilePath;
    private final Path outputLocationPath;
    private final Framework framework;
    private SortedMap<String, List<Node>> ast;
    private MobilangAstParser mobilangAstParser;
    private MobiLangCoder mobilangCoder;
    private Path srcCodeLocation;
    

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
    public Ama(Path mobilangAstFilePath, Path outputLocationPath, String frameworkName) 
    throws FactoryException {
        this.mobilangAstFilePath = mobilangAstFilePath;
        this.outputLocationPath = outputLocationPath;
        this.framework = FrameworkFactory.getInstance(frameworkName);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Path run() 
    throws ParseException, CodeExportException, AppGenerationException, 
    CoderException, IOException {
        readMobilangDotFile();
        parseMobilangAst();
        generateMobilangCode();
        exportMobilangCode();
        generateMobileApplications();

        return outputLocationPath;
    }

    private void readMobilangDotFile() throws FileNotFoundException {
        MobilangDotReader dotReader = new MobilangDotReader();
        
        Consolex.writeInfo("Reading MobiLang AST...");
        dotReader.read(mobilangAstFilePath);
        
        ast = dotReader.getTree();
    }

    private void parseMobilangAst() throws ParseException, IOException {
        mobilangAstParser = new MobilangAstParser(ast);

        Consolex.writeInfo("Parsing MobiLang AST...");
        mobilangAstParser.parse();
    }

    private void generateMobilangCode() throws CoderException {
        mobilangCoder = new MobiLangCoder(
            mobilangAstParser.getScreens(),
            framework
        );
        
        Consolex.writeInfo("Generating code...");
        mobilangCoder.generateCode();
    }

    private void exportMobilangCode() 
    throws CodeExportException {
        MobiLangCodeExport mobilangCodeExport = new FileMobiLangCodeExport(
            mobilangAstParser.getProperties(),
            mobilangCoder.getCodeFiles(),
            mobilangCoder.getDependencies(),
            framework,
            outputLocationPath
        );
        
        Consolex.writeInfo("Exporting code...");

        srcCodeLocation = mobilangCodeExport.export();
    }

    private void generateMobileApplications() throws AppGenerationException {
        Path outputLocation = buildOutputApplicationPath();
        MobiLangAppExport appExport = new MobiLangAppExport(
            framework, 
            srcCodeLocation, 
            outputLocation, 
            mobilangAstParser.getProperties().getTargetPlatforms()
        );
        Consolex.writeInfo("Generating mobile applications...");

        appExport.generateMobileApplications();
    }

    private Path buildOutputApplicationPath() {
        return outputLocationPath.resolve(mobilangAstParser.getProperties().getApplicationName());
    }
}

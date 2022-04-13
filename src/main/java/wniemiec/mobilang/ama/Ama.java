package wniemiec.mobilang.ama;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedMap;
import wniemiec.io.java.Consolex;
import wniemiec.mobilang.ama.coder.MobiLangCoder;
import wniemiec.mobilang.ama.coder.exception.CoderException;
import wniemiec.mobilang.ama.export.FileMobiLangCodeExport;
import wniemiec.mobilang.ama.export.MobiLangCodeExport;
import wniemiec.mobilang.ama.export.exception.CodeExportException;
import wniemiec.mobilang.ama.export.exception.OutputLocationException;
import wniemiec.mobilang.ama.framework.Framework;
import wniemiec.mobilang.ama.framework.FrameworkFactory;
import wniemiec.mobilang.ama.models.Node;
import wniemiec.mobilang.ama.parser.MobiLangAstParser;
import wniemiec.mobilang.ama.parser.exception.FactoryException;
import wniemiec.mobilang.ama.parser.exception.ParseException;
import wniemiec.mobilang.ama.reader.DotReader;


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
    private MobiLangAstParser mobilangAstParser;
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
    throws ParseException, OutputLocationException, CodeExportException, IOException, CoderException {
        readMobilangDotFile();
        parseMobilangAst();
        generateMobilangCode();
        exportMobilangCode();
        generateMobileApplications();

        return outputLocationPath;
    }

    private void readMobilangDotFile() throws FileNotFoundException {
        DotReader dotReader = new DotReader();
        
        Consolex.writeInfo("Reading MobiLang AST...");
        dotReader.read(mobilangAstFilePath);
        
        ast = dotReader.getTree();
    }

    private void parseMobilangAst() throws ParseException, IOException {
        mobilangAstParser = new MobiLangAstParser(ast);

        Consolex.writeInfo("Parsing MobiLang AST...");
        mobilangAstParser.parse();
    }

    private void generateMobilangCode() throws CoderException {
        mobilangCoder = new MobiLangCoder(
            mobilangAstParser.getScreensData(),
            framework
        );
        
        Consolex.writeInfo("Generating code...");
        mobilangCoder.generateCode();
    }

    private void exportMobilangCode() 
    throws OutputLocationException, CodeExportException {
        MobiLangCodeExport mobilangCodeExport = new FileMobiLangCodeExport(
            mobilangAstParser.getPropertiesData(),
            mobilangCoder.getCodeFiles(),
            mobilangCoder.getDependencies(),
            framework,
            outputLocationPath
        );
        
        Consolex.writeInfo("Exporting code...");

        srcCodeLocation = mobilangCodeExport.export();
    }

    private void generateMobileApplications() throws IOException {
        Path outputLocation = buildOutputApplicationPath();

        framework.generateMobileApplications(srcCodeLocation, outputLocation);
    }

    private Path buildOutputApplicationPath() {
        return outputLocationPath.resolve(mobilangAstParser.getPropertiesData().getAppName());
    }
}

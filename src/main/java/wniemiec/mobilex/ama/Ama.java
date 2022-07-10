package wniemiec.mobilex.ama;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedMap;
import wniemiec.io.java.Consolex;
import wniemiec.mobilex.ama.coder.MobilangCoder;
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.export.MobilangAppExport;
import wniemiec.mobilex.ama.export.MobilangCodeExport;
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
    private final Path output;
    private final Framework framework;
    private SortedMap<String, List<Node>> ast;
    private MobilangAstParser astParser;
    private MobilangCoder coder;
    private Path srcCodeLocation;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Manager for ASC compiler pipeline.
     * 
     * @param       mobilangAst MobiLang AST dot file
     * @param       output Path where compiler output will be put
     * @param       framework Framework to be used
     * @throws FactoryException
     */
    public Ama(Path mobilangAst, Path output, String framework) 
    throws FactoryException {
        this.mobilangAstFilePath = mobilangAst;
        this.output = output;
        this.framework = FrameworkFactory.getInstance(framework);
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

        return output;
    }

    private void readMobilangDotFile() throws FileNotFoundException {
        MobilangDotReader dotReader = new MobilangDotReader();
        
        Consolex.writeInfo("Reading MobiLang AST...");
        dotReader.read(mobilangAstFilePath);
        
        ast = dotReader.getTree();
    }

    private void parseMobilangAst() throws ParseException, IOException {
        astParser = new MobilangAstParser(ast);

        Consolex.writeInfo("Parsing MobiLang AST...");
        astParser.parse();
    }

    private void generateMobilangCode() throws CoderException {
        coder = new MobilangCoder(
            astParser.getScreens(),
            framework
        );
        
        Consolex.writeInfo("Generating code...");
        coder.generateCode();
    }

    private void exportMobilangCode() 
    throws CodeExportException {
        MobilangCodeExport mobilangCodeExport = new MobilangCodeExport
            .Builder()
            .properties(astParser.getProperties())
            .codeFiles(coder.getCodeFiles())
            .dependencies(coder.getDependencies())
            .framework(framework)
            .output(output)
            .build();
        
        Consolex.writeInfo("Exporting code...");

        srcCodeLocation = mobilangCodeExport.export();
    }

    private void generateMobileApplications() throws AppGenerationException {
        Path outputLocation = buildOutputApplicationPath();
        MobilangAppExport appExport = new MobilangAppExport
            .Builder()
            .framework(framework)
            .sourceCode(srcCodeLocation)
            .output(outputLocation)
            .platforms(astParser.getProperties().getTargetPlatforms())
            .build();
        
        Consolex.writeInfo("Generating mobile applications...");

        appExport.generateMobileApplications();
    }

    private Path buildOutputApplicationPath() {
        return output.resolve(astParser.getProperties().getApplicationName());
    }
}

package wniemiec.mobilang.asc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.asc.coder.MobilangCoder;
import wniemiec.mobilang.asc.export.ConsoleMobilangCodeExport;
import wniemiec.mobilang.asc.export.FileMobilangCodeExport;
import wniemiec.mobilang.asc.export.MobilangCodeExport;
import wniemiec.mobilang.asc.export.exception.CodeExportException;
import wniemiec.mobilang.asc.export.exception.OutputLocationException;
import wniemiec.mobilang.asc.framework.FrameworkFactory;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.parser.MobilangAstParser;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.reader.DotReader;

public class Asc {

    private Path dotFilePath;
    private Path outputLocationPath;
    private FrameworkFactory frameworkFactory;
    private SortedMap<String, List<Node>> ast;
    private MobilangAstParser mobilangAstParser;
    private MobilangCoder mobilangCoder;
    
    public Asc(Path dotFilePath, Path outputLocationPath, FrameworkFactory frameworkFactory) {
        this.dotFilePath = dotFilePath;
        this.outputLocationPath = outputLocationPath;
        this.frameworkFactory = frameworkFactory;
    }

    public void run() 
    throws ParseException, OutputLocationException, CodeExportException, IOException {
        readMobilangDotFile();
        parseMobilangAst();
        generateMobilangCode();
        exportMobilangCode();
    }

    private void readMobilangDotFile() throws FileNotFoundException {
        DotReader dotReader = new DotReader();
        
        ast = dotReader.read(dotFilePath);
    }


    private void parseMobilangAst() throws ParseException, IOException {
        mobilangAstParser = new MobilangAstParser(ast, frameworkFactory.getParserFactory());
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

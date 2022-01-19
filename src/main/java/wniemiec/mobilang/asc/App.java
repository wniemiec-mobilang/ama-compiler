package wniemiec.mobilang.asc;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedMap;

import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.coder.MobilangCoder;
import wniemiec.mobilang.asc.coder.framework.reactnative.ReactNativeFrameworkCoderFactory;
import wniemiec.mobilang.asc.coder.framework.reactnative.ReactNativeFrameworkScreenCoder;
import wniemiec.mobilang.asc.export.ConsoleMobilangCodeExport;
import wniemiec.mobilang.asc.export.MobilangCodeExport;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.parser.MobilangAstParser;
import wniemiec.mobilang.asc.parser.Parser;
import wniemiec.mobilang.asc.parser.framework.reactnative.ReactNativeFrameworkParserFactory;
import wniemiec.mobilang.asc.reader.DotReader;


public class App 
{
    private static Path dotFilePath;
    private static Path outputLocationPath;


    public static void main(String[] args)
    {
        if (args.length < 2) {
            Consolex.writeError("Missing args! Correct usage: <dot_file>.dot <output_src_location>");
            System.exit(-1);
        }
        
        try {
            parseArgs(args);

            // Call dotReader
            DotReader dotReader = new DotReader();
            SortedMap<String, List<Node>> tree = dotReader.read(dotFilePath);

            // Call dotParser
            MobilangAstParser mobilangAstParser = new MobilangAstParser(tree, new ReactNativeFrameworkParserFactory());
            mobilangAstParser.parse();

            // Call coder
            MobilangCoder mobilangCoder = new MobilangCoder(
                mobilangAstParser.getScreensData(),
                new ReactNativeFrameworkCoderFactory()
            );
            mobilangCoder.generateCode();

            // Call export (file generation)
            MobilangCodeExport mobilangCodeExport = new ConsoleMobilangCodeExport(
                mobilangCoder.getScreensCode(),
                mobilangCoder.getPersistenceCode(),
                mobilangCoder.getCoreCode()
            );
            mobilangCodeExport.export();
        }
        catch (InvalidPathException e) {
            Consolex.writeError("Invalid filepath! " + e.getMessage());
        } 
        catch (Exception e) {
            e.printStackTrace();
            Consolex.writeError(e.getMessage());
        }
    }


    private static void parseArgs(String[] args) {
        dotFilePath = Path.of(args[0]);
        outputLocationPath = Path.of(args[1]);
    }
    
}

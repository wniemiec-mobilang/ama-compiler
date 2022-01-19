package wniemiec.mobilang.asc;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedMap;

import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.parser.DotParser;
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
            //Parser dotParser = new DotParser(dotFilePath, outputLocationPath);
            //dotParser.parse();

            // Call dotReader
            DotReader dotReader = new DotReader();
            SortedMap<String, List<Node>> tree = dotReader.read(dotFilePath);

            // Call dotParser
            Parser dotParser = new DotParser(tree, new ReactNativeFrameworkParserFactory());
            dotParser.parse();

            // Call coder


            // Call export
        }
        catch (InvalidPathException e) {
            Consolex.writeError("Invalid filepath! " + e.getMessage());
        } 
        catch (Exception e) {
            Consolex.writeError(e.getMessage());
            e.printStackTrace();
        }
    }


    private static void parseArgs(String[] args) {
        dotFilePath = Path.of(args[0]);
        outputLocationPath = Path.of(args[1]);
    }
    
}

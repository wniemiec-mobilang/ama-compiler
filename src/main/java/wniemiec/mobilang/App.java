package wniemiec.mobilang;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import wniemiec.mobilang.parser.DotParser;
import wniemiec.mobilang.parser.Parser;

public class App 
{
    public static void main(String[] args)
    {
        if (args.length < 2) {
            System.out.println("Missing args! Correct usage: <dotfile>.dot <output_src_location>");
            System.exit(-1);
        }
        
        try {
            Path dotFilePath = Path.of(args[0]);
            Path outputLocationPath = Path.of(args[1]);
            Parser dotParser = new DotParser(dotFilePath, outputLocationPath);
            dotParser.parse();
        }
        catch (InvalidPathException e) {
            System.out.println("Invalid filepath! " + e.getMessage());
        } 
        catch (Exception e) {
            System.out.println("Error while parsing dotfile! " + e.getMessage());
        }
    }
}

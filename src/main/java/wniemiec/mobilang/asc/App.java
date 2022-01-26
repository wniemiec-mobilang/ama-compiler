package wniemiec.mobilang.asc;

import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.framework.reactnative.ReactNativeFrameworkFactory;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Application point entry. Responsible for parsing CLI arguments and running 
 * ASC compiler.
 */
public class App 
{
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static Path dotFilePath;
    private static Path outputLocationPath;


    //-------------------------------------------------------------------------
    //		Main
    //-------------------------------------------------------------------------
    public static void main(String[] args)
    {
        try {
            parseArgs(args);
            runAsc();
        }
        catch (InvalidPathException e) {
            Consolex.writeError("Invalid filepath! " + e.getMessage());
        }
        catch (ParseException e) {
            Consolex.writeError("Error while parsing: " + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
            //Consolex.writeError(e.getMessage());
        }
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private static void parseArgs(String[] args) {
        validateArgs(args);

        dotFilePath = Path.of(args[0]);
        outputLocationPath = Path.of(args[1]);
    }

    private static void validateArgs(String[] args) {
        if (args.length < 2) {
            Consolex.writeError("Missing args! Correct usage: <dot_file>.dot <output_src_location>");
            System.exit(-1);
        }
    }

    private static void runAsc() throws ParseException, FileNotFoundException {
        Asc asc = new Asc(
            dotFilePath, 
            outputLocationPath,
            new ReactNativeFrameworkFactory()
        );

        asc.run();
    }
}

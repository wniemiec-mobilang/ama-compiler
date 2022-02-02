package wniemiec.mobilang.asc;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.export.exception.CodeExportException;
import wniemiec.mobilang.asc.export.exception.OutputLocationException;
import wniemiec.mobilang.asc.framework.ReactNativeFrameworkFactory;
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
            Consolex.writeError("Invalid dot file location: " + e.getMessage());
        }
        catch (ParseException e) {
            Consolex.writeError("Error while parsing: " + e.getMessage());
            e.printStackTrace();
        }
        catch (OutputLocationException e) {
            Consolex.writeError("Invalid output location: " + e.getMessage());
        }
        catch (CodeExportException e) {
            Consolex.writeError("Error while exporting code: " + e.getMessage());
        }
        catch (Exception e) {
            Consolex.writeError("Fatal error");
            e.printStackTrace();
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

    private static void runAsc() 
    throws ParseException, OutputLocationException, CodeExportException, IOException {
        Asc asc = new Asc(
            dotFilePath, 
            outputLocationPath,
            new ReactNativeFrameworkFactory()
        );

        asc.run();
    }
}

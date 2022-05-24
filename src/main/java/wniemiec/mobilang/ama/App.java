package wniemiec.mobilang.ama;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import wniemiec.io.java.Consolex;
import wniemiec.mobilang.ama.coder.exception.CoderException;
import wniemiec.mobilang.ama.export.exception.AppGenerationException;
import wniemiec.mobilang.ama.export.exception.CodeExportException;
import wniemiec.mobilang.ama.parser.CliParser;
import wniemiec.mobilang.ama.parser.exception.FactoryException;
import wniemiec.mobilang.ama.parser.exception.ParseException;


/**
 * Application point entry. Responsible for parsing CLI arguments and running 
 * ASC compiler.
 */
public class App {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final CliParser CLI_PARSER;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        CLI_PARSER = new CliParser();
    }


    //-------------------------------------------------------------------------
    //		Main
    //-------------------------------------------------------------------------
    public static void main(String[] args) {
        try {
            parseArgs(args);
            runAma();
        }
        catch (IllegalArgumentException e) {
            Consolex.writeError("Invalid cmd args: " + e.getMessage());
            e.printStackTrace();
        }
        catch (FactoryException e) {
            Consolex.writeError("There is no compatibility with this framework: " + CLI_PARSER.getFrameworkName());
        }
        catch (ParseException e) {
            Consolex.writeError("Error while parsing: " + e.getMessage());
            e.printStackTrace();
        }
        catch (CoderException e) {
            Consolex.writeError("Error while generating code: " + e.getMessage());
        }
        catch (CodeExportException e) {
            Consolex.writeError("Error while exporting code: " + e.getMessage());
        }
        catch (AppGenerationException e) {
            Consolex.writeError("Error while generating mobile app: " + e.getMessage());
        }
        catch (Exception e) {
            Consolex.writeError("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private static void parseArgs(String[] args) throws ParseException {
        CLI_PARSER.parse(args);
    }

    private static void runAma() 
    throws ParseException, CodeExportException, IOException, FactoryException, 
    CoderException, AppGenerationException {
        Ama ama = new Ama(
            CLI_PARSER.getMobilangAstFilePath(), 
            CLI_PARSER.getOutputLocationPath(),
            CLI_PARSER.getFrameworkName()
        );

        ama.run();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public static Path getAppRootPath() {
        Path binRootPath = getBinRootPath();

        if (isDevelopmentEnvironment(binRootPath)) {
            return binRootPath
                .getParent()
                .getParent()
                .resolve("src")
                .resolve("main");
        }
        
        return Path.of(binRootPath.toString().split("file:")[1]);
    }

    private static Path getBinRootPath() {
        return getAppBinPath()
            .normalize()
            .toAbsolutePath()
            .getParent()
            .getParent()
            .getParent()
            .getParent();
    }

    private static Path getAppBinPath() {
		return urlToPath(App.class.getResource("App.class"));
	}
	
	private static Path urlToPath(URL url) {
		return new File(url.getPath()).toPath();
	}

    private static boolean isDevelopmentEnvironment(Path binRootPath) {
        return binRootPath
            .getFileName()
            .toString()
            .equals("classes");
    }
}

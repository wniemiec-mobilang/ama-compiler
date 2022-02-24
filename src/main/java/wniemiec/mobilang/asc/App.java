package wniemiec.mobilang.asc;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.export.exception.CodeExportException;
import wniemiec.mobilang.asc.export.exception.OutputLocationException;
import wniemiec.mobilang.asc.parser.exception.FactoryException;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Application point entry. Responsible for parsing CLI arguments and running 
 * ASC compiler.
 */
public class App {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String LBL_MOBILANG_AST;
    private static final String LBL_OUTPUT;
    private static final String LBL_FRAMEWORK_NAME;
    private static Path mobilangAstFilePath;
    private static Path outputLocationPath;
    private static String frameworkName;
    

    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        LBL_MOBILANG_AST = "ast";
        LBL_OUTPUT = "output";
        LBL_FRAMEWORK_NAME = "framework";
    }


    //-------------------------------------------------------------------------
    //		Main
    //-------------------------------------------------------------------------
    public static void main(String[] args) {
        try {
            parseArgs(args);
            runAsc();
        }
        catch (IllegalArgumentException e) {
            Consolex.writeError("Invalid cmd args: " + e.getMessage());
        }
        catch (FactoryException e) {
            Consolex.writeError("There is no compatibility with this framework: " + frameworkName);
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
            Consolex.writeError("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private static void parseArgs(String[] args) 
    throws ParseException, org.apache.commons.cli.ParseException {
        CommandLine cmd = buildCmd(args);

        validateArgs(cmd);

        mobilangAstFilePath = getMobilangAstCliArg(cmd);
        outputLocationPath = getOutputCliArg(cmd);
        frameworkName = getFrameworkName(cmd);
    }

    private static CommandLine buildCmd(String[] args) 
    throws ParseException, org.apache.commons.cli.ParseException {
        Options options = buildCliOptions();
        CommandLineParser parser = new DefaultParser();
        
        return parser.parse(options, args);
    }

    private static Options buildCliOptions() {
        Options options = new Options();

        options.addOption(LBL_MOBILANG_AST, true, "MobiLang AST file (.dot)");
        options.addOption(LBL_OUTPUT, true, "Output location");
        options.addOption(LBL_FRAMEWORK_NAME, true, "Framework name (ex: react-native)");
        
        return options;
    }

    private static void validateArgs(CommandLine cmd) {
        validateCmdOption(cmd, LBL_MOBILANG_AST);
        validateCmdOption(cmd, LBL_OUTPUT);
        validateCmdOption(cmd, LBL_FRAMEWORK_NAME);
    }

    private static void validateCmdOption(CommandLine cmd, String option) {
        if (!cmd.hasOption(option)) {
            throw new IllegalArgumentException(option + " is missing");
        }
    }

    private static Path getMobilangAstCliArg(CommandLine cmd) {
        String mobilangCliArg = cmd.getOptionValue(LBL_MOBILANG_AST);

        return normalizePath(Path.of(mobilangCliArg));
    }

    private static Path normalizePath(Path path) {
        return path.toAbsolutePath().normalize();
    }

    private static Path getOutputCliArg(CommandLine cmd) {
        String outputCliArg = cmd.getOptionValue(LBL_OUTPUT);

        return normalizePath(Path.of(outputCliArg));
    }

    private static String getFrameworkName(CommandLine cmd) {
        return cmd.getOptionValue(LBL_FRAMEWORK_NAME);
    }

    private static void runAsc() 
    throws ParseException, OutputLocationException, CodeExportException, IOException, 
    FactoryException {
        Asc asc = new Asc(
            mobilangAstFilePath, 
            outputLocationPath,
            frameworkName
        );

        asc.run();
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
        
        return binRootPath;
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

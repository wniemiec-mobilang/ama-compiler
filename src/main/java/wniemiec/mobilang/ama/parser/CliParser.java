package wniemiec.mobilang.ama.parser;

import java.nio.file.Path;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.LogLevel;
import wniemiec.mobilang.ama.parser.exception.ParseException;


/**
 * Responsible for parsing Command Line Interface (CLI) arguments.
 */
public class CliParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String LBL_MOBILANG_AST;
    private static final String LBL_OUTPUT;
    private static final String LBL_FRAMEWORK_NAME;
    private static final String LBL_VERBOSE;
    private Path mobilangAstFilePath;
    private Path outputLocationPath;
    private String frameworkName;
    

    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        LBL_MOBILANG_AST = "ast";
        LBL_OUTPUT = "output";
        LBL_FRAMEWORK_NAME = "framework";
        LBL_VERBOSE = "verbose";
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse(String... args) throws ParseException {
        CommandLine cmd = buildCmd(args);

        validateArgs(cmd);
        checkVerboseOption(cmd);

        mobilangAstFilePath = getMobilangAstCliArg(cmd);
        outputLocationPath = getOutputCliArg(cmd);
        frameworkName = getFrameworkName(cmd);
    }

    private CommandLine buildCmd(String[] args) throws ParseException {
        try {
            return parseArgs(args);
        } 
        catch (org.apache.commons.cli.ParseException e) {
            throw new ParseException(e.getMessage());
        }
    }

    private CommandLine parseArgs(String[] args) 
    throws org.apache.commons.cli.ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = buildCliOptions();

        return parser.parse(options, args);
    }

    private Options buildCliOptions() {
        Options options = new Options();

        options.addOption(LBL_MOBILANG_AST, true, "MobiLang AST file (.dot)");
        options.addOption(LBL_OUTPUT, true, "Output location");
        options.addOption(LBL_FRAMEWORK_NAME, true, "Framework name (ex: react-native)");
        options.addOption(LBL_VERBOSE, false, "Display debug messages");
        
        return options;
    }

    private void validateArgs(CommandLine cmd) {
        validateCmdOption(cmd, LBL_MOBILANG_AST);
        validateCmdOption(cmd, LBL_OUTPUT);
        validateCmdOption(cmd, LBL_FRAMEWORK_NAME);
    }

    private void validateCmdOption(CommandLine cmd, String option) {
        if (!cmd.hasOption(option)) {
            throw new IllegalArgumentException(option + " is missing");
        }
    }

    private void checkVerboseOption(CommandLine cmd) {
        if (cmd.hasOption(LBL_VERBOSE)) {
            Consolex.setLoggerLevel(LogLevel.DEBUG);
        }
    }

    private Path getMobilangAstCliArg(CommandLine cmd) {
        String mobilangCliArg = cmd.getOptionValue(LBL_MOBILANG_AST);

        return normalizePath(Path.of(mobilangCliArg));
    }

    private Path normalizePath(Path path) {
        return path.toAbsolutePath().normalize();
    }

    private Path getOutputCliArg(CommandLine cmd) {
        String outputCliArg = cmd.getOptionValue(LBL_OUTPUT);

        return normalizePath(Path.of(outputCliArg));
    }

    private String getFrameworkName(CommandLine cmd) {
        return cmd.getOptionValue(LBL_FRAMEWORK_NAME);
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public Path getMobilangAstFilePath() {
        return mobilangAstFilePath;
    }

    public Path getOutputLocationPath() {
        return outputLocationPath;
    }

    public String getFrameworkName() {
        return frameworkName;
    }
}

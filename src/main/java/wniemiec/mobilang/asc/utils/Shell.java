package wniemiec.mobilang.asc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;


/**
 * Responsible for running shell commands.
 */
public class Shell {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Path workingDirectory;
    private Runtime runtime;
    private String output;
    private String errorOutput;


    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public Shell() {
        this(Path.of(System.getProperty("user.dir")));
        runtime = Runtime.getRuntime();
        output = "";
        errorOutput = "";
    }

    public Shell(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void exec(String... commands) throws IOException {
        Process process = runShell(commands);

        readOutput(process);
        readErrorOutput(process); 
    }

    private Process runShell(String... commands) throws IOException {
        return runtime.exec(
            normalizeCommands(commands), 
            null, 
            workingDirectory.toFile()
        );
    }

    private void readOutput(Process process) throws IOException {
        output = readProcessInputStream(process.getInputStream());
    }

    private String readProcessInputStream(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }

        return builder.toString();
    }

    private void readErrorOutput(Process process) throws IOException {
        errorOutput = readProcessInputStream(process.getErrorStream());
    }

    private String normalizeCommands(String... commands) {
        StringBuilder parsedCommand = new StringBuilder();

        for (String command : commands) {
            parsedCommand.append(command);
            parsedCommand.append(File.pathSeparatorChar);
        }

        if (parsedCommand.length() > 0) {
            parsedCommand.deleteCharAt(parsedCommand.length()-1);
        }

        return parsedCommand.toString();
    }

    public boolean hasError() {
        return !errorOutput.isBlank();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public String getOutput() {
        return output;
    }

    public String getErrorOutput() {
        return errorOutput;
    }
}

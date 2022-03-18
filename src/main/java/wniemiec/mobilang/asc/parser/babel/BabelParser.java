package wniemiec.mobilang.asc.parser.babel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.JarFileManager;
import wniemiec.io.java.StandardTerminalBuilder;
import wniemiec.io.java.Terminal;
import wniemiec.mobilang.asc.App;


/**
 * Responsible for parsing JavaScript ECMA 6, generating JavaScript ECMA 5.
 */
public class BabelParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Path tempFileInput;
    private Path tempFileOutput;
    private Path babelLocation;
    private Terminal terminal;
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    /**
     * Generates JavaScript ECMA 5 from a JavaScript ECMA 6 code.
     * 
     * @param       code JavaScript ECMA 6 code
     * 
     * @return      JavaScript code
     * 
     * @throws      IOException If code cannot be generated
     */
    public List<String> parse(List<String> code) throws IOException {
        setUpTerminal();
        setUpBabel(code);
        runBabel();

        return parsedJavaScriptCode();
    }

    private void setUpBabel(List<String> code) throws IOException {
        tempFileInput = createTempInFileWith(code);
        tempFileOutput = createTempOutFile();
        setUpBabelLocation();
    }

    private Path createTempInFileWith(List<String> code) throws IOException {
        Path tmpDir = createTempJsFile("inputJsCode");
        
        Files.write(tmpDir, code, StandardCharsets.UTF_8);

        return tmpDir;
    }

    private Path createTempJsFile(String name) throws IOException {
        File tmpDir = File.createTempFile(name, "js");

        return tmpDir.toPath();
    }

    private Path createTempOutFile() throws IOException {
        return createTempJsFile("outputJsCode");
    }

    private void setUpTerminal() {
        terminal = StandardTerminalBuilder
            .getInstance()
            .outputHandler(null)
            .outputErrorHandler(Consolex::writeError)
            .build();
    }

    private void setUpBabelLocation() throws IOException {
        Path baseDir = buildBaseDir();

        babelLocation = baseDir
            .resolve("javascript")
            .resolve("babel");
    }

    private static Path buildBaseDir() throws IOException {
        if (!isJarFile()) {
            return App.getAppRootPath();
        }

        Path tmpDir = Path.of(System.getProperty("java.io.tmpdir"));
        JarFileManager jarManager = new JarFileManager(App.getAppRootPath());

        return jarManager.extractTo(tmpDir);
    }

    private static boolean isJarFile() {
        return JarFileManager.isJarFile(App.getAppRootPath());
    }

    private void runBabel() throws IOException {
        terminal.clean();
        terminal.exec(
            "npm",
            "exec",
            "--prefix",
            babelLocation.toAbsolutePath().toString(),
            "--",
            "babel",
            tempFileInput.toAbsolutePath().toString(),
            "-o",
            tempFileOutput.toAbsolutePath().toString()
        );
    }

    private List<String> parsedJavaScriptCode() throws IOException {
        List<String> code = Files.readAllLines(tempFileOutput);

        Files.deleteIfExists(tempFileInput);
        Files.deleteIfExists(tempFileOutput);

        return code;
    }
}
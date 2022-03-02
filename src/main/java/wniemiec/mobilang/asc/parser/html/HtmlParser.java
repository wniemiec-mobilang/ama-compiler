package wniemiec.mobilang.asc.parser.html;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.JarFileManager;
import wniemiec.io.java.StandardTerminalBuilder;
import wniemiec.io.java.Terminal;
import wniemiec.mobilang.asc.App;
import wniemiec.util.java.StringUtils;


/**
 * Responsible for parsing HTML, generating an AST.
 */
public class HtmlParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Path htmlParserLocation;
    private Terminal terminal;
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    /**
     * Generates AST from a HTML code
     * 
     * @param       html Html code
     * 
     * @return      AST
     * 
     * @throws      IOException If AST cannot be generated
     */
    public String parse(String html) throws IOException {
        String normalizedHtml = normalizeHtml(html);
        
        setUpTerminal();
        setUpHtmlParserLocation();
        runHtmlParser(normalizedHtml);

        return parsedHtml();
    }

    private String normalizeHtml(String html) {
        return html
            .replace("\"", "&quot;")
            .replace(" ", "&nbsp;");
    }

    private void setUpTerminal() {
        terminal = StandardTerminalBuilder
            .getInstance()
            .outputHandler(null)
            .outputErrorHandler(Consolex::writeError)
            .build();
    }

    private void setUpHtmlParserLocation() throws IOException {
        Path baseDir = buildBaseDir();

        htmlParserLocation = baseDir
            .resolve("javascript")
            .resolve("html-parser")
            .resolve("index.js");
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

    private void runHtmlParser(String html) throws IOException {
        terminal.clean();
        terminal.exec(
            "node", 
            htmlParserLocation.toString(),
            " \"" + html + "\""
        );
    }

    private String parsedHtml() {
        List<String> terminalOutput = terminal.getHistory();

        return StringUtils.implode(terminalOutput, "");
    }
}

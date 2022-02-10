package wniemiec.mobilang.asc.parser.html;

import java.io.IOException;
import wniemiec.mobilang.asc.utils.Shell;


/**
 * Responsible for parsing HTML, generating an AST.
 */
public class HtmlParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String HTML_PARSER_LOCATION;
    private final Shell shell;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        HTML_PARSER_LOCATION = "./src/main/javascript/html-parser/index.js";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public HtmlParser() {
        shell = new Shell();
    }
    

    //-------------------------------------------------------------------------
    //		Constructor
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
        
        runHtmlParser(normalizedHtml);

        return parsedHtml();
    }

    private String normalizeHtml(String html) {
        return html
            .replace("\"", "&quot;")
            .replace(" ", "&nbsp;");
    }

    private String parsedHtml() {
        if (shell.hasError()) {
            return shell.getErrorOutput();
        }

        return shell.getOutput();
    }

    private void runHtmlParser(String normalizedHtml) throws IOException {
        shell.clean();
        shell.exec("node " + HTML_PARSER_LOCATION + " \"" + normalizedHtml + "\"");
    }
}

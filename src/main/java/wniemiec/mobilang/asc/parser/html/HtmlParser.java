package wniemiec.mobilang.asc.parser.html;

import java.io.IOException;
import java.util.List;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.StandardTerminalBuilder;
import wniemiec.io.java.Terminal;
import wniemiec.util.java.StringUtils;


/**
 * Responsible for parsing HTML, generating an AST.
 */
public class HtmlParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String HTML_PARSER_LOCATION;
    private final Terminal terminal;


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
        terminal = StandardTerminalBuilder
            .getInstance()
            .outputHandler(Consolex::writeInfo)
            .outputErrorHandler(Consolex::writeError)
            .build();
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
        List<String> terminalOutput = terminal.getHistory();

        return StringUtils.implode(terminalOutput, "");
    }

    private void runHtmlParser(String normalizedHtml) throws IOException {
        terminal.clean();
        terminal.exec("node " + HTML_PARSER_LOCATION + " \"" + normalizedHtml + "\"");
    }
}

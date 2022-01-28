package wniemiec.mobilang.asc.parser.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import wniemiec.mobilang.asc.utils.Shell;

public class HtmlParser {

    private Shell shell;
    
    public String parse(String html) throws IOException {
        shell = new Shell();
        //ProcessBuilder pb = new ProcessBuilder("node ./javascript/index.js");
            //System.getProperty("user.dir") + "/src/generate_list.sh", filename);
        //Process process = pb.start();
        String normalizedHtml = html.replaceAll("\"", "&quot;");
        normalizedHtml = normalizedHtml.replaceAll(" ", "&nbsp;");
        
        shell.exec("node ./src/main/javascript/html-parser/index.js \"" + normalizedHtml + "\"");

        return shell.hasError() ? shell.getErrorOutput() : shell.getOutput();
    }
}

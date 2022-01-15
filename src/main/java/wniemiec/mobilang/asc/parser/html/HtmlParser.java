package wniemiec.mobilang.asc.parser.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HtmlParser {
    
    public static String parse(String html) throws IOException {

        //ProcessBuilder pb = new ProcessBuilder("node ./javascript/index.js");
            //System.getProperty("user.dir") + "/src/generate_list.sh", filename);
        //Process process = pb.start();
        Process process = Runtime.getRuntime().exec("node ./src/main/javascript/acorn-parser/index.js \"" + html + "\"");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuilder builder = new StringBuilder();
        StringBuilder errorBuilder = new StringBuilder();
        
        String line = null;
        
        while ( (line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        String errorLine = null;

        while ( (errorLine = errorReader.readLine()) != null) {
            errorBuilder.append(errorLine);
            errorBuilder.append(System.getProperty("line.separator"));
        }

        String result;
        //System.out.println(result);
        
        if (builder.length() == 0) {
            result = errorBuilder.toString();
        }
        else {
            result = builder.toString();
        }

        return result;        
    }
}

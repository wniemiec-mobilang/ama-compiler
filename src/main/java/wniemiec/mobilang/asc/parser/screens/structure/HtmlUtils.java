package wniemiec.mobilang.asc.parser.screens.structure;

import java.io.IOException;

import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.html.HtmlParser;

public class HtmlUtils {

    public static Tag parse(String html) throws IOException, ParseException {
        Tag root = null;

        // a solution could use acorn

        //return root;
        
            HtmlParser htmlParser = new HtmlParser();
            String ast = htmlParser.parse(html);
            //System.out.println(html);
            //System.out.println("AST: " + ast);
            StructureParser structureParser = new StructureParser(ast);
            root = structureParser.parse();
        

        return root;
    }

    
}

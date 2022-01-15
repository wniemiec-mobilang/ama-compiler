package wniemiec.mobilang.asc.parser.screens.structure;

import java.io.IOException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import wniemiec.mobilang.asc.parser.html.HtmlParser;

public class HtmlUtils {

    public static Tag parse(String html) {
        Tag root = null;

        // a solution could use acorn

        //return root;
        try {
            String ast = HtmlParser.parse(html);
            //System.out.println(html);
            //System.out.println("AST: " + ast);
            StructureParser structureParser = new StructureParser(ast);
            root = structureParser.parse();
        } 
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return root;
    }

    
}

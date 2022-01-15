package wniemiec.mobilang.asc.parser.screens.structure;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class HtmlUtils {

    public static Tag parse(String html) {
        //Tag root = null;

        // a solution could use acorn

        //return root;
        return null;
    }

    private static void m() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        // JavaScript code in a String
        String script1 = "function hello(name) {print ('Hello, ' + name);}";
        String script2 = "function getValue(a,b) { if (a===\"Number\") return 1; else return b;}";
        // evaluate script
        try {
            engine.eval(script1);
            engine.eval(script2);

            Invocable inv = (Invocable) engine;

            Object o = inv.invokeFunction("hello", "Scripting!!");  //This one works.      

            //System.out.println(o);
        } 
        catch (ScriptException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}

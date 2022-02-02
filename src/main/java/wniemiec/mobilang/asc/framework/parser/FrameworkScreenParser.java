package wniemiec.mobilang.asc.framework.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import wniemiec.mobilang.asc.models.Behavior;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.models.Variable;
import wniemiec.mobilang.asc.parser.exception.ParseException;

public abstract class FrameworkScreenParser {

    protected String name;
    protected Tag structure;
    protected Style style;
    protected Behavior behavior;
    /*
    protected List<String> imports;
    protected List<Variable> stateDeclarations;
    protected List<String> stateBody;
    protected List<Variable> declarations;
    protected List<String> body;
    */

    protected FrameworkScreenParser(String name, Tag structure, Style style, Behavior behavior) {
        this.name = name;
        this.structure = structure;
        this.style = style;
        this.behavior = behavior;
        /*
        imports = new ArrayList<>();
        stateDeclarations = new ArrayList<>();
        stateBody = new ArrayList<>();
        declarations = new ArrayList<>();
        body = new ArrayList<>();
        */
    }    

    public abstract void parse() throws ParseException, IOException;
    public abstract ScreenData getScreenData();

    /*
    public List<String> getImports() {
        return imports;
    }

    public List<Variable> getDeclarations() {
        return declarations;
    }

    public List<Variable> getStateDeclarations() {
        return stateDeclarations;
    }

    public List<String> getStateBody() {
        return stateBody;
    }

    public List<String> getBody() {
        return body;
    }
    */
}

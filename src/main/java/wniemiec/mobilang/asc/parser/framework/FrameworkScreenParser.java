package wniemiec.mobilang.asc.parser.framework;

import java.util.ArrayList;
import java.util.List;

import wniemiec.mobilang.asc.models.StyleSheet;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.models.Variable;
import wniemiec.mobilang.asc.parser.screens.behavior.Behavior;

public abstract class FrameworkScreenParser {

    protected Tag structure;
    protected StyleSheet style;
    protected Behavior behavior;
    protected List<String> imports;
    protected List<Variable> stateDeclarations;
    protected List<String> stateBody;
    protected List<Variable> declarations;
    protected List<String> body;

    protected FrameworkScreenParser(Tag structure, StyleSheet style, Behavior behavior) {
        this.structure = structure;
        this.style = style;
        this.behavior = behavior;
        imports = new ArrayList<>();
        stateDeclarations = new ArrayList<>();
        stateBody = new ArrayList<>();
        declarations = new ArrayList<>();
        body = new ArrayList<>();
    }    

    public abstract void parse();

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
}

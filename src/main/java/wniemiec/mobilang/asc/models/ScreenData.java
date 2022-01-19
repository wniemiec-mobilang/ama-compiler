package wniemiec.mobilang.asc.models;

import java.util.List;

public class ScreenData {

    private String name;
    private List<String> imports;
    private List<Variable> stateDeclarations;
    private List<String> stateBody;
    private List<Variable> declarations;
    private List<String> body;

    public ScreenData(String name, List<String> imports, List<Variable> stateDeclarations, List<String> stateBody,
            List<Variable> declarations, List<String> body) {
        this.name = name;
        this.imports = imports;
        this.stateDeclarations = stateDeclarations;
        this.stateBody = stateBody;
        this.declarations = declarations;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public List<String> getImports() {
        return imports;
    }

    public List<Variable> getStateDeclarations() {
        return stateDeclarations;
    }
    
    public List<String> getStateBody() {
        return stateBody;
    }
    
    public List<Variable> getDeclarations() {
        return declarations;
    }
    
    public List<String> getBody() {
        return body;
    }
    

    
}

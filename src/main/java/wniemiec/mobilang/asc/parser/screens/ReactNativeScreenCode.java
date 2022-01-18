package wniemiec.mobilang.asc.parser.screens;

import java.util.ArrayList;
import java.util.List;

public class ReactNativeScreenCode {

    String name;
    List<String> imports;
    List<String> declarations;
    List<String> stateDeclarations;
    List<String> effectBody;
    List<String> body;
    
    public ReactNativeScreenCode(String name, List<String> imports, List<String> declarations,
            List<String> stateDeclarations, List<String> effectBody, List<String> body) {
        this.name = name;
        this.imports = imports;
        this.declarations = declarations;
        this.stateDeclarations = stateDeclarations;
        this.effectBody = effectBody;
        this.body = body;
    }
    
    public List<String> generateCode() {
        List<String> code = new ArrayList<>();

        return code;
    }
}

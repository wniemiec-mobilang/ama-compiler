package wniemiec.mobilang.asc.parser.screens;

import java.util.ArrayList;
import java.util.List;

public class ReactNativeScreenCode {

    String name;
    List<String> imports;
    List<Variable> declarations;
    List<Variable> stateDeclarations;
    List<String> effectBody;
    List<String> body;
    
    public ReactNativeScreenCode(
        String name, 
        List<String> imports, 
        List<Variable> declarations,
        List<Variable> stateDeclarations, 
        List<String> effectBody, 
        List<String> body
    ) {
        this.name = name;
        this.imports = imports;
        this.declarations = declarations;
        this.stateDeclarations = stateDeclarations;
        this.effectBody = effectBody;
        this.body = body;

        System.out.println("NAME: " + name);
        System.out.println("IMPORTS: " + imports);
        System.out.println("DECLARATIONS: " + declarations);
        System.out.println("STATE DECLARATIONS: " + stateDeclarations);
        System.out.println("STATE BODY: " + effectBody);
        System.out.println("BODY: " + body);
    }
    
    public List<String> generateCode() {
        List<String> code = new ArrayList<>();

        for (String declaration : imports) {
            code.add(declaration + ";");
        }

        // \n\n
        code.add("");
        code.add("");

        for (Variable declaration : declarations) {
            StringBuilder declarationCode = new StringBuilder();

            declarationCode.append(declaration.getKind());
            declarationCode.append(' ');
            declarationCode.append(declaration.getId());
            declarationCode.append(' ');
            declarationCode.append('=');
            declarationCode.append(' ');
            declarationCode.append(declaration.getContent());
            declarationCode.append(';');

            code.add(declarationCode.toString());
            code.add(""); // \n
        }

        code.add(""); // \n

        code.add("function " + name + " (props) {");
        code.add("");

        for (Variable declaration : stateDeclarations) {
            StringBuilder declarationCode = new StringBuilder();

            declarationCode.append('[');
            declarationCode.append(declaration.getId());
            declarationCode.append(',');
            declarationCode.append("set" + normalize(declaration.getId()));
            declarationCode.append(']');
            declarationCode.append(" = useState(");
            declarationCode.append(declaration.getContent());
            declarationCode.append(");");

            code.add(declarationCode.toString());
        }

        code.add("");
        code.add("useEffect(() => {");

        for (String statement : effectBody) {
            code.add(statement + ";");
        }

        code.add("}, []);");

        code.add("");
        code.add("return (");

        for (String statement : body) {
            code.add(statement);
        }

        code.add(");");


        code.add("}");
        code.add("");
        code.add("export default " + name + ";");
        code.add("");

        return code;
    }

    private String normalize(String id) {
        return capitalize(id.toLowerCase());
    }

    private String capitalize(String str) {
        if (str.isBlank()) {
            return str;
        }

        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}

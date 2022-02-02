package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wniemiec.mobilang.asc.framework.coder.FrameworkScreensCoder;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.Variable;

public class ReactNativeFrameworkScreensCoder extends FrameworkScreensCoder {

    /*
    String name;
    List<String> imports;
    List<Variable> declarations;
    List<Variable> stateDeclarations;
    List<String> effectBody;
    List<String> body;
    */
    
    public ReactNativeFrameworkScreensCoder(List<ScreenData> screensData) {
        super(screensData);
        
        /*
        this.name = screenData.getName();
        this.imports = screenData.getImports();
        this.declarations = screenData.getDeclarations();
        this.stateDeclarations = screenData.getStateDeclarations();
        this.effectBody = screenData.getStateBody();
        this.body = screenData.getBody();
        */

        /*
        System.out.println("NAME: " + name);
        System.out.println("IMPORTS: " + imports);
        System.out.println("DECLARATIONS: " + declarations);
        System.out.println("STATE DECLARATIONS: " + stateDeclarations);
        System.out.println("STATE BODY: " + effectBody);
        System.out.println("BODY: " + body);
        */
    }

    @Override
    public List<FileCode> generateCode() {
        List<FileCode> screensCode = new ArrayList<>();

        for (ScreenData screenData : screensData) {
            String filename = "src/screens/" + screenData.getName() + ".js";

            screensCode.add(new FileCode(filename, generateCodeForScreen(screenData)));
        }

        return screensCode;
    }
    
    

    private List<String> generateCodeForScreen(ScreenData screenData) {
        List<String> code = new ArrayList<>();

        String name = screenData.getName();
        List<String> imports = screenData.getImports();
        List<Variable> declarations = screenData.getDeclarations();
        List<Variable> stateDeclarations = screenData.getStateDeclarations();
        List<String> effectBody = screenData.getStateBody();
        List<String> body = screenData.getBody();

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

        code.add("function " + name + "(props) {");
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

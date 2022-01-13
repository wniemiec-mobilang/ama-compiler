package wniemiec.mobilang.parser.screens.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Declaration extends Instruction {

    String kind;
    List<Declarator> declarations = new ArrayList<>();

    public Declaration(String type, String kind, List<Declarator> declarations) {
        super(type);
        this.kind = kind;

        if (declarations != null)
            this.declarations = declarations;
    }

    @Override
    public String toString() {
        return "Declaration [declarations=" + declarations + ", kind=" + kind + "]";
    }
    
    public String toCode() {
        return kind + " " + declarationsToCode();
    }
    
    private String declarationsToCode() {
        StringBuilder sb = new StringBuilder();
        List<String> argumentsAsCode = declarations.stream().map(a -> a.toCode()).collect(Collectors.toList());

        for (int i = 0; i < argumentsAsCode.size(); i++) {
            sb.append(argumentsAsCode.get(i));
            sb.append(',');
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length()-1);
        }

        return sb.toString();
        //return 
    }
}

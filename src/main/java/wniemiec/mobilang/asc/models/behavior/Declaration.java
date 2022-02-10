package wniemiec.mobilang.asc.models.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Responsible for representing a declaration from behavior code.
 */
public class Declaration implements Instruction {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String kind;
    private final List<Declarator> declarations;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public Declaration(String kind, List<Declarator> declarations) {
        this.kind = kind;
        this.declarations = (declarations == null) ? new ArrayList<>() : declarations;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------    
    @Override
    public String toCode() {
        return kind + " " + declarationsToCode();
    }
    
    private String declarationsToCode() {
        StringBuilder code = new StringBuilder();
        List<String> declarationsAsCode = getDeclarationsAsCode();

        for (int i = 0; i < declarationsAsCode.size(); i++) {
            code.append(declarationsAsCode.get(i));
            code.append(',');
        }

        if (code.length() > 0) {
            code.deleteCharAt(code.length()-1);
        }

        return code.toString();
    }

    private List<String> getDeclarationsAsCode() {
        return declarations
            .stream()
            .map(Declarator::toCode)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Declaration [declarations=" + declarations + ", kind=" + kind + "]";
    }
}

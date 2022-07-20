package wniemiec.mobilex.ama.models.behavior;

import java.util.List;


/**
 * Responsible for representing a class body from behavior code. A class body
 * is the contents of a class.
 */
public class ClassBody implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<Instruction> declarations;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ClassBody(List<Instruction> declarations) {
        this.declarations = declarations;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        return "{" + declarationsToCode() + "}";
    }

    private String declarationsToCode() {
        StringBuilder code = new StringBuilder();

        code.append('\n');

        for (Instruction declaration : declarations) {
            code.append(declaration.toCode());
            code.append('\n');
        }

        return code.toString();
    }

    @Override
    public String toString() {
        return "ClassBody [declarations=" + declarations + "]";
    }
}

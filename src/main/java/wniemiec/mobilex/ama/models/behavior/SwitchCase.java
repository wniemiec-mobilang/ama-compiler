package wniemiec.mobilex.ama.models.behavior;

import java.util.List;

/**
 * Responsible for representing a switch case from behavior code. A switch case
 * is a single case within a SwitchStatement. If test is null, this is the 
 * default case.
 * 
 * Format: case (test): consequent OR default: consequent
 */
public class SwitchCase implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression test;
    private final List<Instruction> consequent;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public SwitchCase(Expression test, List<Instruction> consequent) {
        this.test = test;
        this.consequent = consequent;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        if (test == null) {
            code.append("default: ");
        }
        else {
            code.append("case ");
            code.append(test.toCode());
            code.append(": ");
        }
        
        code.append("\n");
        consequent.forEach(statement -> code.append(statement.toCode() + "\n"));

        return code.toString();
    }
}

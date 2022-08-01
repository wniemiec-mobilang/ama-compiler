package wniemiec.mobilex.ama.models.behavior;

import java.util.List;


/**
 * Responsible for representing a switch statement from behavior code. 
 * 
 * Format: switch (discriminant) { cases }
 * Note: Only the last entry of cases is allowed to be default.
 */
public class SwitchStatement implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression discriminant;
    private final List<SwitchCase> cases;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public SwitchStatement(Expression discriminant, List<SwitchCase> cases) {
        this.discriminant = discriminant;
        this.cases = cases;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("switch (");
        code.append(discriminant.toCode());
        code.append(") {");
        cases.forEach(switchCase -> code.append(switchCase.toCode()));
        code.append("}");

        return code.toString();
    }
}

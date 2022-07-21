package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing break keyword from behavior code.
 */
public class BreakStatement implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression label;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public BreakStatement(Expression label) {
        this.label = label;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("break");

        if (label != null) {
            code.append(" " + label.toCode());
        }

        return code.toString();
    }
}

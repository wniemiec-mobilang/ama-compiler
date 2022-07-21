package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing continue keyword from behavior code.
 */
public class ContinueStatement implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression label;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ContinueStatement(Expression label) {
        this.label = label;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("continue");

        if (label != null) {
            code.append(" " + label.toCode());
        }

        return code.toString();
    }
}

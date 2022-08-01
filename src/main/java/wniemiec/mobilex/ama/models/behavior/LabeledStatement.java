package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing a labeled statement from behavior code. A
 * labeled statement is a statement prefixed by a label.
 * 
 * Format: label: body
 */
public class LabeledStatement implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Identifier label;
    private final Instruction body;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public LabeledStatement(Identifier label, Instruction body) {
        this.label = label;
        this.body = body;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append(label.toCode());
        code.append(": ");
        code.append(body.toCode());

        return code.toString();
    }
}

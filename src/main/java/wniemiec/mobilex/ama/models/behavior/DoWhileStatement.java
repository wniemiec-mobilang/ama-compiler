package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing a do while statement from behavior code.
 * 
 * Format: do body while (test)
 */
public class DoWhileStatement implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression test;
    private final Instruction body;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public DoWhileStatement(Expression test, Instruction body) {
        this.test = test;
        this.body = body;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("do ");
        code.append(body.toCode());
        code.append("while (");
        code.append(test.toCode());
        code.append(")");

        return code.toString();
    }
}

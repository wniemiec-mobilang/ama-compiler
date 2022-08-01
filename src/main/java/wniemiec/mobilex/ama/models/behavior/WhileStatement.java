package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing a while statement from behavior code.
 * 
 * Format: while (test) body
 */
public class WhileStatement implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression test;
    private final Instruction body;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public WhileStatement(Expression test, Instruction body) {
        this.test = test;
        this.body = body;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("while (");
        code.append(test.toCode());
        code.append(") ");
        code.append(body.toCode());

        return code.toString();
    }
}

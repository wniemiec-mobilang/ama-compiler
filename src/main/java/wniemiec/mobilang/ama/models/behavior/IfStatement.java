package wniemiec.mobilang.ama.models.behavior;


/**
 * Responsible for representing a if statement from behavior code.
 */
public class IfStatement implements Instruction {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression test;
    private final Instruction body;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IfStatement(Expression test, Instruction body) {
        this.test = test;
        this.body = body;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("if (");
        code.append(test.toCode());
        code.append(") " );
        code.append(body.toCode());

        return code.toString();
    }
}

package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing a if statement from behavior code.
 */
public class IfStatement extends Instruction {

    private Expression test;
    private Instruction body;

    public IfStatement(Expression test, Instruction body) {
        super("IfStatement");
        this.test = test;
        this.body = body;
    }

    @Override
    public String toCode() {
        return "if (" + test.toCode() + ") " + body.toCode();
    }

}

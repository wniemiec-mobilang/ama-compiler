package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing a for in declaration from behavior code.
 */
public class ForInDeclaration extends Instruction {

    private Instruction left;
    private Expression right; 
    private Instruction body;

    public ForInDeclaration(
        Instruction left, 
        Expression right, 
        Instruction body
    ) {
        super("ForInDeclaration");
        this.left = left;
        this.right = right;
        this.body = body;
    }

    @Override
    public String toCode() {
        return "for (" + left.toCode() + " in " + right.toCode() + ") " + body.toCode();
    }

}

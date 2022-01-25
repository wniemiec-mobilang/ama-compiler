package wniemiec.mobilang.asc.parser.screens.behavior;

public class ForOfDeclaration extends Instruction {

    private Instruction left;
    private Expression right; 
    private Instruction body;

    public ForOfDeclaration(
        Instruction left, 
        Expression right, 
        Instruction body
    ) {
        super("ForOfDeclaration");
        this.left = left;
        this.right = right;
        this.body = body;
    }

    @Override
    public String toCode() {
        return "for (" + left.toCode() + " of " + right.toCode() + ") " + body.toCode();
    }

}

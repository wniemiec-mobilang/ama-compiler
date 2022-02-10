package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing a for of declaration from behavior code.
 */
public class ForOfDeclaration implements Instruction {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Instruction left;
    private final Expression right; 
    private final Instruction body;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ForOfDeclaration(
        Instruction left, 
        Expression right, 
        Instruction body
    ) {
        this.left = left;
        this.right = right;
        this.body = body;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("for (");
        code.append(left.toCode());
        code.append(" of ");
        code.append(right.toCode());
        code.append(") ");
        code.append(body.toCode());

        return code.toString();
    }

}

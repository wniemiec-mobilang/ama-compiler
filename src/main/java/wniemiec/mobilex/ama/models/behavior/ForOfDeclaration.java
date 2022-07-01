package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing a for of declaration from behavior code.
 */
public class ForOfDeclaration implements Instruction {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Instruction left;
    private final Instruction body;
    private final Expression right; 


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

package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing an assignment expression from behavior code.
 */
public class AssignmentExpression implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String operator;
    private final Expression left;
    private final Expression right;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public AssignmentExpression(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append(left == null ? "null" : left.toCode());
        code.append(operator);
        code.append(right == null ? "null" : right.toCode());

        return code.toString();
    }
    
    @Override
    public String toString() {
        return  "[AssignmentExpression] {" 
            + "[" 
                + left 
                + " " 
                + operator 
                + right 
            + "] }";
    }
}

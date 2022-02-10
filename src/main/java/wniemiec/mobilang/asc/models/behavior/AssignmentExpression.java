package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing an assignment expression from behavior code.
 */
public class AssignmentExpression extends Expression {
    String operator;
    Expression left;
    Expression right;

    public AssignmentExpression(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public String toCode() {
        return left.toCode() + operator + right.toCode();
    }
    
    public String toString() {
        return  "[AssignmentExpression] {" + "[" + left + " " + operator + right + "] }";

    }
}

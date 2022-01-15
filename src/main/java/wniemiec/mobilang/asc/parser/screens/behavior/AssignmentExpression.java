package wniemiec.mobilang.asc.parser.screens.behavior;

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

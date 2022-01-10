package wniemiec.mobilang.parser.screens.behavior;

class AssignmentExpression extends Expression {
    String operator;
    Expression left;
    Expression right;

    public AssignmentExpression(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public String toCode() {
        return "[" + left + " " + operator + right + "]";
    }    
}

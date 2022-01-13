package wniemiec.mobilang.parser.screens.behavior;

class Statement extends Instruction {

    Expression expression;
    
    public Statement(String type, Expression expression) {
        super(type);
        this.expression = expression;
        //TODO Auto-generated constructor stub

    }

    @Override
    public String toString() {
        return "Statement [expression=" + expression + "]";
    }

    public String toCode() {
        return expression.toCode();
    }
}
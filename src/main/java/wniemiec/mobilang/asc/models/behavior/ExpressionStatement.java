package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing an expression statement from behavior code.
 */
public class ExpressionStatement extends Instruction {

    private Expression expression;
    
    public ExpressionStatement(Expression expression) {
        super("ExpressionStatement");
        this.expression = expression;

    }

    @Override
    public String toString() {
        return "ExpressionStatement [expression=" + expression + "]";
    }

    public String toCode() {
        return expression.toCode();
    }
}
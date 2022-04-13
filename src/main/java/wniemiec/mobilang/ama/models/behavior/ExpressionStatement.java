package wniemiec.mobilang.ama.models.behavior;


/**
 * Responsible for representing an expression statement from behavior code.
 */
public class ExpressionStatement implements Instruction {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression expression;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ExpressionStatement(Expression expression) {
        this.expression = expression;

    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        return expression.toCode();
    }

    @Override
    public String toString() {
        return "ExpressionStatement [expression=" + expression + "]";
    }
}
package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for parsing unary expressions from behavior code from Mobilang 
 * AST.
 */
public class UnaryExpression implements Expression {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String operator;
    private final boolean prefix; 
    private final Expression argument;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public UnaryExpression(String operator, boolean prefix, Expression argument) {
        this.operator = operator;
        this.prefix = prefix;
        this.argument = argument;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        if (prefix) {
            return operator + argument.toCode();    
        }

        return argument.toCode() + operator;
    }
}

package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing an update expression from behavior code.
 */
public class UpdateExpression implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String operator;
    private final boolean prefix; 
    private final Expression argument;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public UpdateExpression(String operator, boolean prefix, Expression argument) {
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

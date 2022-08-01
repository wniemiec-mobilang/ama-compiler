package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing a spread element from behavior code. A spread
 * element is used in a CallExpression.
 * 
 * Format: ...argument
 */
public class SpreadElement implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression argument;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public SpreadElement(Expression argument) {
        this.argument = argument;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("...");
        code.append(argument.toCode());

        return code.toString();
    }
}

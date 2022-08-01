package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing throw keyword from behavior code.
 * 
 * Format: throw argument
 */
public class ThrowStatement implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression argument;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ThrowStatement(Expression argument) {
        this.argument = argument;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("throw ");
        code.append(argument.toCode());

        return code.toString();
    }
}

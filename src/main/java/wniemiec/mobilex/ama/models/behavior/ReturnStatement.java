package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing a return call.
 */
public class ReturnStatement implements Instruction {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression argument;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReturnStatement(Expression argument) {
        this.argument = argument;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        if (argument == null) {
            return "return";
        }

        return "return "  + argument.toCode();
    }

    @Override
    public String toString() {
        return "ReturnStatement [argument=" + argument + "]";
    }
}
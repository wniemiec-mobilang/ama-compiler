package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing an empty statement from behavior code. An empty
 * statement is a solitary semicolon, and it does not generates useful code.
 */
public class EmptyStatement implements Instruction {

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        return ";";
    }
}

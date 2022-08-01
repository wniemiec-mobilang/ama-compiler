package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing an assignment property from behavior code. Just 
 * like a Property, but kind is always init.
 */
public class AssignmentProperty implements Expression {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Identifier key;
    private final Expression value;
    private final boolean shorthand;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public AssignmentProperty(Identifier key, Expression value, boolean shorthand) {
        this.key = key;
        this.value = value;
        this.shorthand = shorthand;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append(key.toCode());

        if (!shorthand) {
            code.append(": ");
            code.append(value.toCode());
        }

        return code.toString();
    }
}

package wniemiec.mobilang.ama.models.behavior;


/**
 * Responsible for representing an identifier from behavior code.
 */
public class Identifier implements Expression {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String name;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public Identifier(String name) {
        this.name = name;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        return name;
    }    
    
    @Override
    public String toString() { 
        return  "[IdentifierExpression] {" + "[Identifier: " + name + "] }";
    }
}
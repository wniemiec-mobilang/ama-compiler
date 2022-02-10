package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing a literal from behavior code.
 */
public class Literal implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String value;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public Literal(String value) {
        this.value = value;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        return "\"" + value + "\"";
    }
    
    @Override
    public String toString() {
        return  "[LiteralExpression] {" +  "[Literal: " + value + "] }";
    }
}
package wniemiec.mobilang.ama.models.behavior;


/**
 * Responsible for representing a literal from behavior code.
 */
public class Literal implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String value;
    private final boolean number;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private Literal(String value, boolean number) {
        this.value = value;
        this.number = number;
    }


    //-------------------------------------------------------------------------
    //		Factories
    //-------------------------------------------------------------------------
    public static Literal ofString(String value) {
        return new Literal(value, false);
    }

    public static Literal ofNumber(String value) {
        return new Literal(value, true);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        if (number) {
            return value;
        }
        
        return "\"" + value + "\"";
    }
    
    @Override
    public String toString() {
        return  "[LiteralExpression] {" +  "[Literal: " + value + "] }";
    }
}
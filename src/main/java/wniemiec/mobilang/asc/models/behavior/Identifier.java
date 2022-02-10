package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing an identifier from behavior code.
 */
public class Identifier extends Expression {
    String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String toCode() {
        return name;
    }    
    
    public String toString() {
        
        return  "[IdentifierExpression] {" + "[Identifier: " + name + "] }";
    }
}
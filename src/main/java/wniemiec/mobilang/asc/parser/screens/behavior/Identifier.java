package wniemiec.mobilang.asc.parser.screens.behavior;

class Identifier extends Expression {
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
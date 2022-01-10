package wniemiec.mobilang.parser.screens.behavior;

class Identifier extends Expression {
    String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String toCode() {
        return "[Identifier: " + name + "]";
    }    
}
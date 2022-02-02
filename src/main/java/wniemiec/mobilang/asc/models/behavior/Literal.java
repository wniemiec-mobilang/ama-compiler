package wniemiec.mobilang.asc.models.behavior;

public class Literal extends Expression {
    public Literal(String value) {
        this.value = value;
    }

    String value;

    @Override
    public String toCode() {
        return "\"" + value + "\"";
    }
    
    public String toString() {
        
        return  "[LiteralExpression] {" +  "[Literal: " + value + "] }";
    }
}
package wniemiec.mobilang.parser.screens.behavior;

class Literal extends Expression {
    public Literal(String value) {
        this.value = value;
    }

    String value;

    @Override
    public String toCode() {
        return "[Literal: " + value + "]";
    }
}
package wniemiec.mobilang.parser.screens.behavior;

abstract class Expression {

    public abstract String toCode();

    @Override
    public String toString() {
        return toCode();
    }
}
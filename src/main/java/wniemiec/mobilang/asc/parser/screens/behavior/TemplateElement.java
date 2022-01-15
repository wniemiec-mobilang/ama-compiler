package wniemiec.mobilang.asc.parser.screens.behavior;

class TemplateElement extends Expression {
    public TemplateElement(String value, boolean tail) {
        this.value = value;
        this.tail = tail;
    }

    String value;
    boolean tail;

    @Override
    public String toString() {
        return  "[TemplateElement] {" + "{value: " + value + "; tail: " + tail + "} }";
    }

    @Override
    public String toCode() {
        return  value;
    }
}
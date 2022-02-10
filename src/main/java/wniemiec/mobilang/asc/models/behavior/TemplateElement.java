package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing a template element from behavior code.
 */
public class TemplateElement implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String value;
    private final boolean tail;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public TemplateElement(String value, boolean tail) {
        this.value = value;
        this.tail = tail;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        return value;
    }

    @Override
    public String toString() {
        return "[TemplateElement] {" + "{value: " + value + "; tail: " + tail + "} }";
    }
}
package wniemiec.mobilang.ama.models.behavior;


/**
 * Responsible for representing a template element from a template string.
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
    /**
     * Represents a template element from a template string.
     * 
     * @param       value Element content
     * @param       tail True if the element is at the end of a template 
     * string; false otherwise
     */
    public TemplateElement(String value, boolean tail) {
        this.value = value;
        this.tail = tail;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public boolean isTail() {
        return tail;
    }

    @Override
    public String toCode() {
        return value;
    }

    @Override
    public String toString() {
        return "[TemplateElement] {" + "{value: " + value + "; tail: " + tail + "} }";
    }
}
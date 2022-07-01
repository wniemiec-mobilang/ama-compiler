package wniemiec.mobilex.ama.models;


/**
 * Represents a tag with events (like onclick) in a String.
 */
public class InlineEventTag extends EventTag {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private String code;


    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public InlineEventTag(String code) {
        this.code = code;
    }

    //-------------------------------------------------------------------------
    //		Getters & Setters
    //-------------------------------------------------------------------------
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
package wniemiec.mobilang.ama.models;


/**
 * Represents a tag with events (like onclick) in a String.
 */
public class InlineEventTag {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String NULL_VALUE;
    private String code;
    private String id;
    private String eventName;
    private String eventValue;


    //-------------------------------------------------------------------------
    //		Initialization blocks
    //-------------------------------------------------------------------------
    static {
        NULL_VALUE = "NULL";
    }


    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public InlineEventTag(String code) {
        this.code = code;
        id = NULL_VALUE;
        eventName = NULL_VALUE;
        eventValue = NULL_VALUE;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public boolean hasEvent() {
        return (eventName != NULL_VALUE);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventValue() {
        return eventValue;
    }

    public void setEventValue(String eventValue) {
        this.eventValue = eventValue;
    }
}
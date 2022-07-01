package wniemiec.mobilex.ama.models;


/**
 * Represents a tag with events (like onclick).
 */
public class EventTag {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String NULL_VALUE;
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
    public EventTag() {
        this(NULL_VALUE, NULL_VALUE, NULL_VALUE);
    }


    public EventTag(String id, String eventName, String eventValue) {
        this.id = id;
        this.eventName = eventName;
        this.eventValue = eventValue;
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
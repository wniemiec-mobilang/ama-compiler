package wniemiec.mobilex.ama.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class EventTagTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private EventTag eventTag;
    private String id;
    private String name;
    private String value;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        eventTag = null;
        id = null;
        name = null;
        value = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testGetId() {
        withId("some-id");
        buildEventTag();
        assertGetIdIsCorrect();
    }

    @Test
    void testGetName() {
        withName("onclick");
        buildEventTag();
        assertGetNameIsCorrect();
    }

    @Test
    void testGetValue() {
        withValue("alert(`Hey! You pressed me!`)");
        buildEventTag();
        assertGetValueIsCorrect();
    }

    @Test
    void testHasEvent() {
        withId("some-id");
        withName("onclick");
        withValue("alert(`Hey! You pressed me!`)");
        buildEventTag();
        assertHasEvent();
    }

    @Test
    void testDoesNotHaveEvent() {
        buildEventTag();
        assertDoesNotHaveEvent();
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withId(String tagId) {
        id = tagId;
    }

    private void withName(String eventName) {
        name = eventName;
    }

    private void withValue(String eventValue) {
        value = eventValue;
    }

    private void buildEventTag() {
        eventTag = new EventTag(id, name, value);
    }

    private void assertGetIdIsCorrect() {
        Assertions.assertEquals(id, eventTag.getId());
    }

    private void assertGetNameIsCorrect() {
        Assertions.assertEquals(name, eventTag.getEventName());
    }

    private void assertGetValueIsCorrect() {
        Assertions.assertEquals(value, eventTag.getEventValue());
    }

    private void assertHasEvent() {
        Assertions.assertTrue(eventTag.hasEvent());
    }

    private void assertDoesNotHaveEvent() {
        Assertions.assertTrue(eventTag.hasEvent());
    }
}

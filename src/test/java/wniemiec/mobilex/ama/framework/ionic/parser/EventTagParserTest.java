package wniemiec.mobilex.ama.framework.ionic.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.tag.Tag;


class EventTagParserTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private EventTagParser parser;
    private Tag rootTag;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = new EventTagParser();
        rootTag = Tag.getEmptyInstance();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testButtonWithOnClick() {
        withRootTag(buildButtonWithOnClickAndValue("foo"));
        doParsing();
        assertDoesNotContainsAttribute("onclick");
        assertHasEvent("onclick");
    }

    @Test
    void testNullTag() {
        withRootTag(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            doParsing();
        });
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Tag buildButtonWithOnClickAndValue(String value) {
        Tag buttonTag = Tag.getNormalInstance("button");
        
        buttonTag.addAttribute("onclick", "alert('hey!! you pressed the button!')");
        buttonTag.setValue(value);
        
        return buttonTag;
    }

    private void withRootTag(Tag tag) {
        rootTag = tag;
    }

    private void doParsing() {
        parser.parse(rootTag);
    }

    private void assertDoesNotContainsAttribute(String attribute) {
        Assertions.assertFalse(parser.getParsedTag().hasAttribute(attribute));
    }

    private void assertHasEvent(String event) {
        Assertions.assertTrue(containsEvent(event));
    }

    private boolean containsEvent(String event) {
        return parser
            .getEvents()
            .stream()
            .filter(e -> e.getEventName().equals(event))
            .findAny()
            .isPresent();
    }
}

package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilang.ama.models.tag.Tag;


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
        Assertions.assertTrue(parser.getEvents().containsKey(event));
    }
}

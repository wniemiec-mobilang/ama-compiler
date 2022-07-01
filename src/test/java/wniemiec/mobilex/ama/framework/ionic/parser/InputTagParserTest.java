package wniemiec.mobilex.ama.framework.ionic.parser;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.framework.ionic.parser.InputTagParser;
import wniemiec.mobilex.ama.models.tag.Tag;


class InputTagParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private InputTagParser parser;
    private Tag rootTag;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        rootTag = Tag.getEmptyInstance();
        parser = new InputTagParser();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testSimpleInputWithId() {
        withRootTag(buildInputWithId("foo"));
        doParsing();
        assertContainsInputIds("input_foo");
    }

    @Test
    void testSimpleInputWithoutId() {
        withRootTag(buildInputWithoutId());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            doParsing();
        });
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Tag buildInputWithId(String id) {
        Tag inputTag = Tag.getVoidInstance("input");
        
        inputTag.addAttribute("id", id);
        
        return inputTag;
    }

    private void withRootTag(Tag tag) {
        rootTag = tag;
    }

    private void doParsing() {
        parser.parse(rootTag);
    }

    private void assertContainsInputIds(String... expectedInputIds) {
        List<String> obtainedInputIds = parser.getInputIds();

        for (String expectedId : expectedInputIds) {
            Assertions.assertTrue(obtainedInputIds.contains(expectedId));
        }
    }

    private Tag buildInputWithoutId() {
        return Tag.getVoidInstance("input");
    }
}

package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilang.ama.models.tag.Tag;


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
        parser = new InputTagParser();
        rootTag = Tag.getEmptyInstance();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testSimpleInputWithId() {
        withRootTag(buildButtonWithOnClickAndId());
        doParsing();
        assertParsedTagDoesNotHaveOnClickAttribute();
        assertContainsInputIds("input_fooId");
    }

    @Test
    void testSimpleInputWithoutId() {
        withRootTag(buildButtonWithOnClick());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            doParsing();
        });
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Tag buildButtonWithOnClickAndId() {
        Tag buttonTag = new Tag("button");
        
        buttonTag.addAttribute("onclick", "alert('hey!! you pressed the button!')");
        buttonTag.addAttribute("id", "fooId");
        
        return buttonTag;
    }

    private void withRootTag(Tag tag) {
        rootTag = tag;
    }

    private void doParsing() {
        parser.parse(rootTag);
    }

    private void assertParsedTagDoesNotHaveOnClickAttribute() {
        Assertions.assertFalse(parser.getParsedTag().hasAttribute("onclick"));
    }

    private void assertContainsInputIds(String... expectedInputIds) {
        List<String> obtainedInputIds = parser.getInputIds();

        for (String expectedId : expectedInputIds) {
            Assertions.assertTrue(obtainedInputIds.contains(expectedId));
        }
    }

    private Tag buildButtonWithOnClick() {
        Tag buttonTag = new Tag("button");
        
        buttonTag.addAttribute("onclick", "alert('hey!! you pressed the button!')");
        
        return buttonTag;
    }
}

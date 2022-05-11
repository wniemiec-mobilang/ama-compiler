package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilang.ama.models.tag.Tag;

class InputTagParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private InputTagParser parser;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = new InputTagParser();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testSimpleInputWithId() {
        Map<String, String> attributes = new HashMap<>();
        
        attributes.put("onclick", "alert('hey!! you pressed the button!')");
        attributes.put("id", "fooId");
        Tag buttonTag = new Tag("button", attributes);

        parser.parse(buttonTag);

        List<String> expectedInputIds = List.of("input_fooId");

        Assertions.assertFalse(parser.getParsedTag().hasAttribute("onclick"));
        Assertions.assertEquals(expectedInputIds, parser.getInputIds());
    }
}

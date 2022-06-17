package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilang.ama.models.tag.Tag;


class IonicStructureParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private IonicStructureParser parser;
    private Tag rootTag;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        rootTag = Tag.getEmptyInstance();
        parser = new IonicStructureParser();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testInputWithId() {
        withRootTag(buildInputWithId("foo"));
        doParsing();
        assertCodeEquals(
            "<ion-input [(ngModel)]=\"input_foo\" id=\"foo\">",
            "</ion-input>"
        );
        assertContainsInputIds("input_foo");
    }

    @Test
    void testButtonWithOnClick() {
        withRootTag(buildButtonWithOnClickAndValue("Foo"));
        doParsing();
        assertHasEvent("onclick");
    }

    @Test
    void testLinkTagWithHrefWithScreenDirective() {
        withRootTag(buildLinkWithHrefWithScreenDirective());
        doParsing();
        assertCodeEquals("<a href=\"home\"/>");
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
    private Tag buildInputWithId(String id) {
        Tag inputTag = Tag.getVoidInstance("input");
        
        inputTag.addAttribute("id", id);
        
        return inputTag;
    }

    private Tag buildButtonWithOnClickAndValue(String value) {
        Tag buttonTag = Tag.getNormalInstance("button");
        
        buttonTag.addAttribute("onclick", "alert('hey!! you pressed the button!')");
        buttonTag.setValue(value);
        
        return buttonTag;
    }

    private Tag buildLinkWithHrefWithScreenDirective() {
        Tag aTag = Tag.getVoidInstance("a");
        
        aTag.addAttribute("href", "mobilang::screen::home");
        
        return aTag;
    }

    private void withRootTag(Tag tag) {
        rootTag = tag;
    }

    private void doParsing() {
        parser.parse(rootTag);
    }

    private void assertCodeEquals(String... lines) {
        List<String> expectedCode = Arrays.asList(lines);

        assertHasSameSize(expectedCode, parser.getParsedCode());
        assertHasSameLines(expectedCode, parser.getParsedCode());
    }

    private void assertHasSameSize(List<String> expected, List<String> obtained) {
        Assertions.assertEquals(expected.size(), obtained.size());
    }

    private void assertHasSameLines(List<String> expected, List<String> obtained) {
        for (int i = 0; i < expected.size(); i++) {            
            assertHasSameLine(expected.get(i), obtained.get(i));
        }
    }

    private void assertHasSameLine(String expected, String obtained) {
        Assertions.assertEquals(
            removeWhiteSpaces(expected),
            removeWhiteSpaces(obtained)
        );
    }

    private String removeWhiteSpaces(String text) {
        return text.replaceAll("[\\s\\t]+", "");
    }

    private void assertContainsInputIds(String... expectedInputIds) {
        List<String> obtainedInputIds = parser.getInputIds();

        for (String expectedId : expectedInputIds) {
            Assertions.assertTrue(obtainedInputIds.contains(expectedId));
        }
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

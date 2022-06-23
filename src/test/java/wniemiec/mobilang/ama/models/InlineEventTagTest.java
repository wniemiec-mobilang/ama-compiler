package wniemiec.mobilang.ama.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class InlineEventTagTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private InlineEventTag inlineEventTag;
    private String code;
    

    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        inlineEventTag = null;
        code = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testGetCode() {
        withCode("alert('check the console'); console.log('hello world!');");
        buildInlineEventTag();
        assertGetCodeIsCorrect();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withCode(String inlineCode) {
        code = inlineCode;
    }

    private void buildInlineEventTag() {
        inlineEventTag = new InlineEventTag(code);
    }

    private void assertGetCodeIsCorrect() {
        assertHasSameLine(code, inlineEventTag.getCode());
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
}

package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TemplateElementTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private TemplateElement templateElement;
    private String value;
    private boolean tail;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        templateElement = null;
        value = null;
        tail = false;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCode() {
        withValue("hello world");
        buildTemplateElement();
        assertToCodeIs("hello world");
    }

    @Test
    void testToCodeWithTailValue() {
        withTailValue("hello world");
        buildTemplateElement();
        assertIsTail();
        assertToCodeIs("hello world");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withValue(String value) {
        this.value = value;
        tail = false;
    }

    private void buildTemplateElement() {
        templateElement = new TemplateElement(value, tail);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, templateElement.toCode());
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

    private void withTailValue(String value) {
        this.value = value;
        tail = true;
    }

    private void assertIsTail() {
        Assertions.assertTrue(templateElement.isTail());
    }
}

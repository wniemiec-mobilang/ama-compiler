package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.models.behavior.Literal;


class LiteralTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Literal literal;
    private String value;
    private boolean number;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        literal = null;
        value = null;
        number = false;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithNumericValue() {
        withNumericValue("7");
        buildLiteral();
        assertToCodeIs("7");
    }

    @Test
    void testToCodeWithTextValue() {
        withTextValue("some text");
        buildLiteral();
        assertToCodeIs("\"some text\"");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withNumericValue(String value) {
        this.value = value;
        number = true;
    }

    private void buildLiteral() {
        literal = number ? Literal.ofNumber(value) : Literal.ofString(value);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, literal.toCode());
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

    private void withTextValue(String value) {
        this.value = value;
        number = false;
    }
}

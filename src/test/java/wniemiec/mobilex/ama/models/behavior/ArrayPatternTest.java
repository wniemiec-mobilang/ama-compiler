package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ArrayPatternTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ArrayPattern arrayPattern;
    private List<Expression> elements;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        arrayPattern = null;
        elements = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithOneElement() {
        withElement(new Identifier("index"));
        buildArrayExpression();
        assertToCodeIs("[index]");
    }

    @Test
    void testToCodeWithTwoElements() {
        withElement(Literal.ofNumber("0"));
        withElement(Literal.ofNumber("1"));
        buildArrayExpression();
        assertToCodeIs("[0, 1]");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withElement(Expression expression) {
        elements.add(expression);
    }

    private void buildArrayExpression() {
        arrayPattern = new ArrayPattern(elements);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, arrayPattern.toCode());
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

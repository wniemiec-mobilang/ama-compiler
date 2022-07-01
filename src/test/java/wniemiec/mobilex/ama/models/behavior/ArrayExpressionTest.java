package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ArrayExpressionTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ArrayExpression arrayExpression;
    private List<Expression> elements;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        arrayExpression = null;
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
        arrayExpression = new ArrayExpression(elements);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, arrayExpression.toCode());
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

package wniemiec.mobilang.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ConditionalExpressionTest {
 
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ConditionalExpression conditionalExpression;
    private Expression test;
    private Expression consequent;
    private Expression alternate;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        test = null;
        consequent = null;
        alternate = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithTestAndConsequentAndAlternate() {
        withTest(buildCallExpression("hasSomething"));
        withConsequent(new Identifier("foo"));
        withAlternate(new Identifier("bar"));
        buildConditionalExpression();
        assertToCodeIs("hasSomething() ? foo : bar");
    }

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Expression buildCallExpression(String label) {
        return new CallExpression(new Identifier(label));
    }

    private void withTest(Expression expression) {
        test = expression;
    }

    private void withConsequent(Expression expression) {
        consequent = expression;
    }

    private void withAlternate(Expression expression) {
        alternate = expression;
    }

    private void buildConditionalExpression() {
        conditionalExpression = new ConditionalExpression(test, consequent, alternate);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, conditionalExpression.toCode());
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

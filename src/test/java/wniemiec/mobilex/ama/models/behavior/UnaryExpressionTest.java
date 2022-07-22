package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UnaryExpressionTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private UnaryExpression unaryExpression;
    private String operator;
    private boolean prefix;
    private Expression argument;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        unaryExpression = null;
        operator = null;
        prefix = false;
        argument = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithOperatorAndPrefixAndArgument() {
        withOperator("!");
        withPrefix(true);
        withArgument(new Identifier("isEmpty"));
        buildUnaryExpression();
        assertToCodeIs("!isEmpty");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withOperator(String operator) {
       this.operator = operator;
    }

    private void withPrefix(boolean value) {
        prefix = value;
    }

    private void withArgument(Expression expression) {
        argument = expression;
    }

    private void buildUnaryExpression() {
        unaryExpression = new UnaryExpression(operator, prefix, argument);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, unaryExpression.toCode());
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

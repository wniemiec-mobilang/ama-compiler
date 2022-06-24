package wniemiec.mobilang.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UpdateExpressionTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private UpdateExpression updateExpression;
    private String operator;
    private boolean prefix;
    private Expression argument;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        updateExpression = null;
        operator = null;
        prefix = false;
        argument = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testPostIncrement() {
        withOperator("++");
        withPrefix(false);
        withArgument(new Identifier("i"));
        buildUpdateExpression();
        assertToCodeIs("i++");
    }

    @Test
    void testPreIncrement() {
        withOperator("++");
        withPrefix(true);
        withArgument(new Identifier("i"));
        buildUpdateExpression();
        assertToCodeIs("++i");
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

    private void buildUpdateExpression() {
        updateExpression = new UpdateExpression(operator, prefix, argument);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, updateExpression.toCode());
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

package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.models.behavior.CallExpression;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.Identifier;


class CallExpressionTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private CallExpression callExpression;
    private Expression callee;
    private List<Expression> arguments;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        callExpression = null;
        callee = null;
        arguments = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithoutParameters() {
        withCallee(new Identifier("printResults"));
        buildCallExpression();
        assertToCodeIs("printResults()");
    }

    @Test
    void testToCodeWithOneParameter() {
        withCallee(new Identifier("invert"));
        withArgument(new Identifier("n1"));
        buildCallExpression();
        assertToCodeIs("invert(n1)");
    }


    @Test
    void testToCodeWithTwoParameters() {
        withCallee(new Identifier("sum"));
        withArgument(new Identifier("n1"));
        withArgument(new Identifier("n2"));
        buildCallExpression();
        assertToCodeIs("sum(n1, n2)");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withCallee(Expression expression) {
        callee = expression;
    }

    private void buildCallExpression() {
        callExpression = new CallExpression(callee, arguments);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, callExpression.toCode());
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

    private void withArgument(Expression expression) {
        arguments.add(expression);
    }
}

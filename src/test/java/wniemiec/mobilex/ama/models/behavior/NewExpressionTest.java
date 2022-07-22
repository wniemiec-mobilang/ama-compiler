package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class NewExpressionTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private NewExpression newExpression;
    private Expression callee;
    private List<Expression> arguments;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        newExpression = null;
        callee = null;
        arguments = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithCalleeAndOneArgument() {
        withCallee(new Identifier("Integer"));
        withArgument(Literal.ofNumber("2"));
        buildNewExpression();
        assertToCodeIs("new Integer(2)");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withCallee(Expression expression) {
        callee = expression;
    }

    private void withArgument(Expression expression) {
        if (expression == null) {
            arguments = null;
        }
        else {
            arguments.add(expression);
        }
    }

    private void buildNewExpression() {
        newExpression = new NewExpression(callee, arguments);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, newExpression.toCode());
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

package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FunctionExpressionTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private FunctionExpression functionExpression;
    private boolean async;
    private List<Expression> params;
    private Instruction bodyCode;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        functionExpression = null;
        async = false;
        params = new ArrayList<>();
        bodyCode = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithAsyncFunctionAndBodyAndParams() {
        withAsync(true);
        withParameters(new Identifier("a"), new Identifier("b"));
        withBody(new ReturnStatement(new Identifier("a")));
        buildFunctionExpression();
        assertToCodeIs("async (a, b) return a");
    }

    @Test
    void testToCodeWithBodyAndParams() {
        withAsync(false);
        withParameters(new Identifier("a"), new Identifier("b"));
        withBody(new ReturnStatement(new Identifier("a")));
        buildFunctionExpression();
        assertToCodeIs("(a, b) return a");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withAsync(boolean value) {
        async = value;
    }

    private void withParameters(Expression... parameters) {
        Arrays.stream(parameters).forEach(params::add);
    }

    private void withBody(Instruction instruction) {
        bodyCode = instruction;
    }

    private void buildFunctionExpression() {
        functionExpression = new FunctionExpression(async, params, bodyCode);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, functionExpression.toCode());
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

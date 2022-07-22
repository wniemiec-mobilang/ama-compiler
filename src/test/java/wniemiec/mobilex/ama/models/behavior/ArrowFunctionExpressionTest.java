package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ArrowFunctionExpressionTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ArrowFunctionExpression arrowExpression;
    private boolean async;
    private List<Expression> params;
    private Instruction body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        arrowExpression = null;
        async = false;
        params = new ArrayList<>();
        body = null;
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
        assertToCodeIs("async (a, b) => return a");
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
        body = instruction;
    }

    private void buildFunctionExpression() {
        arrowExpression = new ArrowFunctionExpression(async, params, body);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, arrowExpression.toCode());
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

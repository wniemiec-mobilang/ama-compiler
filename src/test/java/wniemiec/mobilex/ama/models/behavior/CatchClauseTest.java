package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CatchClauseTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private CatchClause catchClause;
    private Expression param;
    private BlockStatement body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        catchClause = null;
        param = null;
        body = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithParamAndBody() {
        withParam(new Identifier("err"));
        withBody(buildBlockStatement(buildPostIncrementExpression("errors")));
        buildCatchClause();
        assertToCodeIs("catch(err) { errors++; }");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withParam(Expression expression) {
        param = expression;
    }

    private Expression buildPostIncrementExpression(String label) {
        return new UpdateExpression("++", false, new Identifier(label));
    }

    private BlockStatement buildBlockStatement(Expression expression) {
        return new BlockStatement(new ExpressionStatement(expression));
    }

    private void withBody(BlockStatement blockStatement) {
        body = blockStatement;
    }

    private void buildCatchClause() {
        catchClause = new CatchClause(param, body);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, catchClause.toCode());
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

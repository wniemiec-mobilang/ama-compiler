package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TryStatementTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private TryStatement tryStatement;
    private BlockStatement block;
    private BlockStatement finalizer;
    private CatchClause handler;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        tryStatement = null;
        block = null;
        finalizer = null;
        handler = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithBlockAndHandlerAndFinalizer() {
        withBlock(buildBlockStatement(buildPostIncrementExpression("total")));
        withHandler(
            buildCatchClause(
                new Identifier("err"),
                buildBlockStatement(buildPostIncrementExpression("errors"))
            )
        );
        withFinalizer(buildBlockStatement(buildPostIncrementExpression("round")));
        buildTryStatement();
        assertToCodeIs("try { total++; } catch(err) { errors++; } finally { round++; }");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Expression buildPostIncrementExpression(String label) {
        return new UpdateExpression("++", false, new Identifier(label));
    }

    private BlockStatement buildBlockStatement(Expression expression) {
        return new BlockStatement(new ExpressionStatement(expression));
    }

    private void withBlock(BlockStatement blockStatement) {
        block = blockStatement;
    }

    private CatchClause buildCatchClause(Expression param, BlockStatement body) {
        return new CatchClause(param, body);
    }

    private void withHandler(CatchClause catchClause) {
        handler = catchClause;
    }

    private void withFinalizer(BlockStatement blockStatement) {
        finalizer = blockStatement;
    }

    private void buildTryStatement() {
        tryStatement = new TryStatement(block, handler, finalizer);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, tryStatement.toCode());
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

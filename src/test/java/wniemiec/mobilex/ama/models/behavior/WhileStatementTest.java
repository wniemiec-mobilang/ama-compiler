package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class WhileStatementTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private WhileStatement whileStatement;
    private Expression test;
    private Instruction body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        whileStatement = null;
        test = null;
        body = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithTestAndBody() {
        withTest(buildCallExpression("hasItems"));
        withBody(buildBlockStatement(buildPostIncrementExpression("total")));
        buildWhileStatement();
        assertToCodeIs("while(hasItems()) { total++; }");
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

    private Expression buildPostIncrementExpression(String label) {
        return new UpdateExpression("++", false, new Identifier(label));
    }

    private Instruction buildBlockStatement(Expression expression) {
        return new BlockStatement(new ExpressionStatement(expression));
    }

    private void withBody(Instruction instruction) {
        body = instruction;
    }

    private void buildWhileStatement() {
        whileStatement = new WhileStatement(test, body);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, whileStatement.toCode());
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

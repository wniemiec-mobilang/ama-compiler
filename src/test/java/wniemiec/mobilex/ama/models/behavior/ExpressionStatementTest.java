package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ExpressionStatementTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ExpressionStatement expressionStatement;
    private Expression expression;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        expressionStatement = null;
        expression = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithExpression() {
        withExpression(buildPostIncrementExpression("i"));
        buildExpressionStatementDeclaration();
        assertToCodeIs("i++");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withExpression(Expression expression) {
        this.expression = expression;
    }

    private Expression buildPostIncrementExpression(String label) {
        return new UpdateExpression("++", false, new Identifier(label));
    }

    private void buildExpressionStatementDeclaration() {
        expressionStatement = new ExpressionStatement(expression);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, expressionStatement.toCode());
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

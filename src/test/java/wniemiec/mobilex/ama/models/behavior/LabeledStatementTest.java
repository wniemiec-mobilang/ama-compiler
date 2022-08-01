package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LabeledStatementTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private LabeledStatement labeledStatement;
    private Identifier label;
    private Instruction body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        labeledStatement = null;
        label = null;
        body = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithLabelAndBody() {
        withLabel(buildIdentifier("hasItems"));
        withBody(buildBlockStatement(buildPostIncrementExpression("total")));
        buildLabeledStatement();
        assertToCodeIs("hasItems: { total++; }");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Identifier buildIdentifier(String label) {
        return new Identifier(label);
    }

    private void withLabel(Identifier identifier) {
        label = identifier;
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

    private void buildLabeledStatement() {
        labeledStatement = new LabeledStatement(label, body);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, labeledStatement.toCode());
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

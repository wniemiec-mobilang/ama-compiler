package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.models.behavior.AssignmentExpression;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.Identifier;
import wniemiec.mobilex.ama.models.behavior.Literal;


class AssignmentExpressionTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private AssignmentExpression assignmentExpression;
    private String operator;
    private Expression left;
    private Expression right;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        assignmentExpression = null;
        operator = null;
        left = null;
        right = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithOperatorAndLeftAndRight() {
        withOperator("=");
        withLeft(new Identifier("index"));
        withRight(Literal.ofNumber("0"));
        buildAssignmentExpression();
        assertToCodeIs("index = 0");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withOperator(String operator) {
        this.operator = operator;
    }

    private void withLeft(Expression expression) {
        left = expression;
    }

    private void withRight(Expression expression) {
        right = expression;
    }

    private void buildAssignmentExpression() {
        assignmentExpression = new AssignmentExpression(operator, left, right);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, assignmentExpression.toCode());
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

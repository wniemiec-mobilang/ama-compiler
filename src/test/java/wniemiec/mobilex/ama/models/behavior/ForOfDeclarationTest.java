package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.models.behavior.Declaration;
import wniemiec.mobilex.ama.models.behavior.Declarator;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.ExpressionStatement;
import wniemiec.mobilex.ama.models.behavior.ForOfDeclaration;
import wniemiec.mobilex.ama.models.behavior.Identifier;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.models.behavior.UpdateExpression;


class ForOfDeclarationTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ForOfDeclaration forOfDeclaration;
    private Instruction left;
    private Instruction body;
    private Expression right;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        forOfDeclaration = null;
        left = null;
        body = null;
        right = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithLeftAndBodyAndRight() {
        withLeft(buildVariableDeclaration("item"));
        withBody(buildStatement(buildPostIncrementExpression("total")));
        withRight(new Identifier("items"));
        buildForOfDeclaration();
        assertToCodeIs("for (let item of items) total++");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Instruction buildVariableDeclaration(String label) {
        Declarator declaration = new Declarator("let", "object", label, null);

        return new Declaration("let", declaration);
    }

    private void withLeft(Instruction instruction) {
        left = instruction;
    }

    private Expression buildPostIncrementExpression(String label) {
        return new UpdateExpression("++", false, new Identifier(label));
    }

    private Instruction buildStatement(Expression expression) {
        return new ExpressionStatement(expression);
    }

    private void withBody(Instruction instruction) {
        body = instruction;
    }

    private void withRight(Expression expression) {
        right = expression;
    }

    private void buildForOfDeclaration() {
        forOfDeclaration = new ForOfDeclaration(left, right, body);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, forOfDeclaration.toCode());
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

package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ForDeclarationTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ForDeclaration forDeclaration;
    private Instruction init;
    private Instruction body;
    private Expression test;
    private Expression update;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        forDeclaration = null;
        init = null;
        body = null;
        test = null;
        update = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithInitAndTestAndUpdateAndBody() {
        withInit(buildVariableDeclaration("i", Literal.ofNumber("0")));
        withTest(buildCallExpression("hasItems"));
        withUpdate(buildPostIncrementExpression("i"));
        withBody(buildStatement(buildPostIncrementExpression("total")));
        buildForDeclaration();
        assertToCodeIs("for (let i = 0; hasItems(); i++) total++;");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Instruction buildVariableDeclaration(String label, Expression value) {
        Declarator declaration = new Declarator("let", "object", label, value);

        return new Declaration("let", declaration);
    }

    private void withInit(Instruction instruction) {
        init = instruction;
    }

    private Expression buildCallExpression(String label) {
        return new CallExpression(new Identifier(label));
    }

    private void withTest(Expression expression) {
        test = expression;
    }

    private Expression buildPostIncrementExpression(String label) {
        return new UpdateExpression("++", false, new Identifier(label));
    }

    private Instruction buildStatement(Expression expression) {
        return new ExpressionStatement(expression);
    }

    private void withUpdate(Expression expression) {
        update = expression;
    }

    private void withBody(Instruction instruction) {
        body = instruction;
    }

    private void buildForDeclaration() {
        forDeclaration = new ForDeclaration(init, test, update, body);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, forDeclaration.toCode());
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

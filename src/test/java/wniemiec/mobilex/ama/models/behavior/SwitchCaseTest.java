package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SwitchCaseTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private SwitchCase switchCase;
    private Expression test;
    private List<Instruction> consequent;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        switchCase = null;
        test = null;
        consequent = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithTestAndConsequent() {
        withTest(Literal.ofNumber("2"));
        withConsequent(buildExpressionStatement(buildPostIncrementExpression("total")));
        buildSwitchCase();
        assertToCodeIs("case 2: \n total++;");
    }

    @Test
    void testToCodeWithonsequent() {
        withTest(null);
        withConsequent(buildExpressionStatement(buildPostIncrementExpression("total")));
        buildSwitchCase();
        assertToCodeIs("default: \n total++;");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withTest(Expression expression) {
        test = expression;
    }

    private Expression buildPostIncrementExpression(String label) {
        return new UpdateExpression("++", false, new Identifier(label));
    }

    private Instruction buildExpressionStatement(Expression expression) {
        return new ExpressionStatement(expression);
    }

    private void withConsequent(Instruction instruction) {
        consequent.add(instruction);
    }

    private void buildSwitchCase() {
        switchCase = new SwitchCase(test, consequent);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, switchCase.toCode());
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

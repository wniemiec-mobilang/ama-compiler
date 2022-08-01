package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SwitchStatementTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private SwitchStatement switchStatement;
    private List<SwitchCase> cases;
    private Expression discriminant;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        switchStatement = null;
        cases = new ArrayList<>();
        discriminant = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithDiscriminantAndOneCase() {
        withDiscriminant(buildIdentifier("hasItems"));
        withCase(
            buildSwitchCase(
                Literal.ofNumber("2"), 
                buildExpressionStatement(buildPostIncrementExpression("total"))
            )
        );
        buildSwitchStatement();
        assertToCodeIs("switch (hasItems) { case 2: \n total++; }");
    }

    @Test
    void testToCodeWithDiscriminantAndOneCaseAndDefault() {
        withDiscriminant(buildIdentifier("hasItems"));
        withCase(
            buildSwitchCase(
                Literal.ofNumber("2"), 
                buildExpressionStatement(buildPostIncrementExpression("total"))
            )
        );
        withCase(
            buildSwitchCase(
                null, 
                buildBreakStatement()
            )
        );
        buildSwitchStatement();
        assertToCodeIs("switch (hasItems) { case 2: \n total++; default: break }");
    }


    private Instruction buildBreakStatement() {
        return new BreakStatement(null);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Expression buildIdentifier(String label) {
        return new Identifier(label);
    }

    private void withDiscriminant(Expression expression) {
        discriminant = expression;
    }

    private SwitchCase buildSwitchCase(Expression test, Instruction... consequent) {
        return new SwitchCase(test, Arrays.asList(consequent));
    }

    private Instruction buildExpressionStatement(Expression expression) {
        return new ExpressionStatement(expression);
    }

    private Expression buildPostIncrementExpression(String label) {
        return new UpdateExpression("++", false, new Identifier(label));
    }

    private void withCase(SwitchCase switchCase) {
        cases.add(switchCase);
    }

    private void buildSwitchStatement() {
        switchStatement = new SwitchStatement(discriminant, cases);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, switchStatement.toCode());
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

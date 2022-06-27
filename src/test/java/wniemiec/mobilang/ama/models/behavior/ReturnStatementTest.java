package wniemiec.mobilang.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ReturnStatementTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ReturnStatement returnStatement;
    private Expression argument;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        returnStatement = null;
        argument = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithArgument() {
        withArgument(new Identifier("result"));
        buildReturnStatement();
        assertToCodeIs("return result");
    }

    @Test
    void testToCodeWithoutArgument() {
        withArgument(null);
        buildReturnStatement();
        assertToCodeIs("return");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withArgument(Expression expression) {
        argument = expression;
    }

    private void buildReturnStatement() {
        returnStatement = new ReturnStatement(argument);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, returnStatement.toCode());
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

package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ThrowStatementTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ThrowStatement throwStatement;
    private Expression argument;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        throwStatement = null;
        argument = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithArgument() {
        withArgument(buildIdentifier("foo"));
        buildThrowStatement();
        assertToCodeIs("throw foo");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Identifier buildIdentifier(String name) {
        return new Identifier(name);
    }
    
    private void withArgument(Expression label) {
        this.argument = label;
    }

    private void buildThrowStatement() {
        throwStatement = new ThrowStatement(argument);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, throwStatement.toCode());
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

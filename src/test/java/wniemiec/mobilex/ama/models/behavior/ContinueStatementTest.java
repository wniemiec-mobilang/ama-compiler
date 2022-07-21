package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ContinueStatementTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ContinueStatement continueStatement;
    private Expression label;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        label = null;
        continueStatement = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithLabel() {
        withLabel(buildIdentifier("foo"));
        buildContinueStatement();
        assertToCodeIs("continue foo");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Identifier buildIdentifier(String name) {
        return new Identifier(name);
    }
    
    private void withLabel(Expression label) {
        this.label = label;
    }

    private void buildContinueStatement() {
        continueStatement = new ContinueStatement(label);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, continueStatement.toCode());
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

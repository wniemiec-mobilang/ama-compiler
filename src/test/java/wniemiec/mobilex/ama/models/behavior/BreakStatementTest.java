package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BreakStatementTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private BreakStatement breakStatement;
    private Expression label;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        label = null;
        breakStatement = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithLabel() {
        withLabel(buildIdentifier("foo"));
        buildBreakStatement();
        assertToCodeIs("break foo");
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

    private void buildBreakStatement() {
        breakStatement = new BreakStatement(label);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, breakStatement.toCode());
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

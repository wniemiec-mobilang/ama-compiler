package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class EmptyStatementTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private EmptyStatement emptyStatement;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        emptyStatement = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCode() {
        buildEmptyStatement();
        assertToCodeIs(";");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void buildEmptyStatement() {
        emptyStatement = new EmptyStatement();
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, emptyStatement.toCode());
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

package wniemiec.mobilex.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class IfStatementTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private IfStatement ifStatement;
    private Expression test;
    private Instruction body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        ifStatement = null;
        test = null;
        body = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithTestAndBody() {
        withTest(new Identifier("hasSum"));
        withBody(new ReturnStatement(new Identifier("sum")));
        buildIfStatement();
        assertToCodeIs("if (hasSum) return sum");
    }

    @Test
    void testToCodeWithTest() {
        withTest(new Identifier("hasSum"));
        withBody(null);
        buildIfStatement();
        assertToCodeIs("if (hasSum) {}");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withTest(Expression test) {
        this.test = test;
    }

    private void withBody(Instruction body) {
        this.body = body;
    }

    private void buildIfStatement() {
        ifStatement = new IfStatement(test, body);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, ifStatement.toCode());
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

package wniemiec.mobilang.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BlockStatementTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private BlockStatement blockStatement;
    private List<Instruction> body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        blockStatement = null;
        body = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithBodyWithOneLine() {
        withInstruction(buildVariableDeclaration("index", Literal.ofNumber("0")));
        buildBlockStatement();
        assertToCodeIs("{ let index = 0 }");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Instruction buildVariableDeclaration(String label, Expression value) {
        Declarator declaration = new Declarator("let", "object", label, value);

        return new Declaration("let", declaration);
    }

    private void withInstruction(Instruction instruction) {
        body.add(instruction);
    }

    private void buildBlockStatement() {
        blockStatement = new BlockStatement(body);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, blockStatement.toCode());
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

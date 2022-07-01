package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.models.behavior.Behavior;
import wniemiec.mobilex.ama.models.behavior.Declaration;
import wniemiec.mobilex.ama.models.behavior.Declarator;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.models.behavior.Literal;


class BehaviorTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Behavior behavior;
    private List<Instruction> instructions;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        behavior = null;
        instructions = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithOneInstruction() {
        withInstruction(buildVariableDeclaration("index", Literal.ofNumber("0")));
        buildBehavior();
        assertToCodeIs("let index = 0;");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Instruction buildVariableDeclaration(String label, Expression value) {
        Declarator declaration = new Declarator("let", "object", label, value);

        return new Declaration("let", declaration);
    }

    private void withInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

    private void buildBehavior() {
        behavior = new Behavior(instructions);
    }

    private void assertToCodeIs(String... code) {
        List<String> expectedCode = Arrays.asList(code);
        List<String> obtainedCode = behavior.toCode();

        assertHasSameSize(expectedCode, obtainedCode);
        assertHasSameLines(expectedCode, obtainedCode);
    }

    private void assertHasSameSize(List<String> expected, List<String> obtained) {
        Assertions.assertEquals(expected.size(), obtained.size());
    }

    private void assertHasSameLines(List<String> expected, List<String> obtained) {
        for (int i = 0; i < expected.size(); i++) {            
            assertHasSameLine(expected.get(i), obtained.get(i));
        }
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

package wniemiec.mobilex.ama.models.behavior;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MethodDefinitionTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private MethodDefinition methodDefinition;
    private boolean computed;
    private boolean isStatic;
    private String kind;
    private Expression key;
    private Expression value;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        methodDefinition = null;
        computed = false;
        isStatic = false;
        kind = null;
        key = null;
        value = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithKeyAndValueAndStaticAndComputed() {
        withKey(new Identifier("getFirst"));
        withComputed(false);
        withStatic(false);
        withKind("method");
        withValue(
            buildSyncFunction(
                new ReturnStatement(new Identifier("a")), 
                new Identifier("a"), 
                new Identifier("b")
            )
        );
        buildMethodDefinition();
        assertToCodeIs("getFirst(a, b) { return a }");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withComputed(boolean value) {
        computed = value;
    }

    private void withStatic(boolean value) {
        isStatic = value;
    }

    private void withKind(String kind) {
        this.kind = kind;
    }

    private void withKey(Expression expression) {
        key = expression;
    }

    private void withValue(Expression value) {
        this.value = value;
    }

    private Expression buildSyncFunction(Instruction body, Expression... params) {
        return new FunctionExpression(false, Arrays.asList(params), body);
    }

    private void buildMethodDefinition() {
        methodDefinition = new MethodDefinition(
            computed, 
            isStatic, 
            kind, 
            key, 
            value
        );
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, methodDefinition.toCode());
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

    private void assertIsComputed() {
        Assertions.assertTrue(methodDefinition.isComputed());
    }

    private void assertIsNotComputed() {
        Assertions.assertFalse(methodDefinition.isComputed());
    }
}

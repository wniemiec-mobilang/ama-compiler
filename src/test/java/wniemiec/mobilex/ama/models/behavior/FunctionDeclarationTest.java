package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FunctionDeclarationTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private FunctionDeclaration functionDeclaration;
    private String name;
    private boolean async;
    private List<Expression> params;
    private Instruction bodyCode;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        functionDeclaration = null;
        name = null;
        async = false;
        params = new ArrayList<>();
        bodyCode = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithAsyncFunctionAndNameAndBodyAndParams() {
        withAsync(true);
        withName("getFirst");
        withParameters(new Identifier("a"), new Identifier("b"));
        withBody(buildBlockCode(new ReturnStatement(new Identifier("a"))));
        buildFunctionExpression();
        assertToCodeIs("async function getFirst(a, b) { return a }");
    }

    @Test
    void testToCodeWithNameAndBodyAndParams() {
        withAsync(false);
        withName("getFirst");
        withParameters(new Identifier("a"), new Identifier("b"));
        withBody(buildBlockCode(new ReturnStatement(new Identifier("a"))));
        buildFunctionExpression();
        assertToCodeIs("function getFirst(a, b) { return a }");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withName(String name) {
        this.name = name;
    }

    private void withAsync(boolean value) {
        async = value;
    }

    private void withParameters(Expression... parameters) {
        Arrays.stream(parameters).forEach(params::add);
    }

    private Instruction buildBlockCode(Instruction instruction) {
        return new BlockStatement(List.of(instruction));
    }

    private void withBody(Instruction instruction) {
        bodyCode = instruction;
    }

    private void buildFunctionExpression() {
        functionDeclaration = new FunctionDeclaration(name, async, params, bodyCode);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, functionDeclaration.toCode());
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

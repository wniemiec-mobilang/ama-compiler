package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ClassBodyTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ClassBody classBody;
    private List<Instruction> declarations;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        classBody = null;
        declarations = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithOneDeclaration() {
        withDeclaration(
            buildSyncMethodDefinition(
                "getFirst",
                new ReturnStatement(new Identifier("a")), 
                new Identifier("a"), 
                new Identifier("b")
            )
        );
        buildClassBody();
        assertToCodeIs("{ getFirst(a, b) { return a } }");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withDeclaration(Instruction declaration) {
        declarations.add(declaration);
    }

    private Instruction buildSyncMethodDefinition(
        String name, 
        Instruction body, 
        Expression... params
    ) {
        return new MethodDefinition(
            false, 
            false, 
            "method", 
            new Identifier(name), 
            buildSyncFunction(body, params)
        );
    }

    private Expression buildSyncFunction(Instruction body, Expression... params) {
        return new FunctionExpression(false, Arrays.asList(params), body);
    }

    private void buildClassBody() {
        classBody = new ClassBody(declarations);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, classBody.toCode());
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

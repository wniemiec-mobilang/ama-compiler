package wniemiec.mobilex.ama.models.behavior;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ClassDeclarationTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ClassDeclaration classDeclaration;
    private Expression id;
    private Expression superClass;
    private Instruction body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        classDeclaration = null;
        id = null;
        superClass = null;
        body = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithIdAndSuperClassAndBody() {
        withId(new Identifier("StringUtils"));
        withSuperClass(new Identifier("Utilities"));
        withBody(
            buildSyncMethodDefinition(
                "getFirst",
                new ReturnStatement(new Identifier("a")), 
                new Identifier("a"), 
                new Identifier("b")
            )
        );
        buildClassDeclaration();
        assertToCodeIs("class StringUtils extends Utilities { getFirst(a, b) { return a } }");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withId(Expression expression) {
        id = expression;
    }

    private void withSuperClass(Expression expression) {
        superClass = expression;
    }

    private void withBody(Instruction... declarations) {
        body = new ClassBody(Arrays.asList(declarations));
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

    private void buildClassDeclaration() {
        classDeclaration = new ClassDeclaration(id, superClass, body);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, classDeclaration.toCode());
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

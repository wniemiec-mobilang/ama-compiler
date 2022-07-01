package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.models.behavior.Declaration;
import wniemiec.mobilex.ama.models.behavior.Declarator;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.Literal;


class DeclarationTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Declaration declaration;
    private String kind;
    private List<Declarator> declarations;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        declaration = null;
        kind = null;
        declarations = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithTypeAndIdKindAndIdNameAndInit() {
        withKind("let");
        withDeclaration(buildVariableDeclaration("i", Literal.ofNumber("0")));
        buildDeclaration();
        assertToCodeIs("let i = 0");
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withKind(String kind) {
        this.kind = kind;
    }

    private Declarator buildVariableDeclaration(String label, Expression value) {
        return new Declarator("let", "object", label, value);
    }

    private void withDeclaration(Declarator declaration) {
        declarations.add(declaration);
    }

    private void buildDeclaration() {
        declaration = new Declaration(kind, declarations);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, declaration.toCode());
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

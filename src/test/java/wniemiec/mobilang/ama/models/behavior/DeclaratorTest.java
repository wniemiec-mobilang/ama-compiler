package wniemiec.mobilang.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DeclaratorTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Declarator declarator;
    private String type;
    private String idKind;
    private String idName;
    private Expression init;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        declarator = null;
        type = null;
        idKind = null;
        idName = null;
        init = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithTypeAndIdKindAndIdNameAndInit() {
        withType("let");
        withIdKind("object");
        withIdName("i");
        withInit(Literal.ofNumber("0"));
        buildDeclarator();
        assertToCodeIs("i = 0");
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withType(String type) {
        this.type = type;
    }

    private void withIdKind(String kind) {
        idKind = kind;
    }

    private void withIdName(String name) {
        idName = name;
    }

    private void withInit(Expression expression) {
        init = expression;
    }

    private void buildDeclarator() {
        declarator = new Declarator(type, idKind, idName, init);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, declarator.toCode());
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

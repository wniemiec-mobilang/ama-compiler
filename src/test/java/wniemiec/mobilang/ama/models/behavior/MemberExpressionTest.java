package wniemiec.mobilang.ama.models.behavior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MemberExpressionTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private MemberExpression memberExpression;
    private Expression object;
    private String type;
    private String name;
    private int value;
    private boolean computed;
    private boolean optional;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        memberExpression = null;
        object = null;
        type = null;
        name = null;
        value = 0;
        computed = false;
        optional = false;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithPropertyName() {
        withObject(new Identifier("x"));
        withType("string");
        withName("index");
        withComputed(false);
        withOptional(false);
        buildMemberExpression();
        assertToCodeIs("x.index");
    }

    @Test
    void testToCodeWithPropertyNameAndComputed() {
        withObject(new Identifier("x"));
        withType("string");
        withName("index");
        withComputed(true);
        withOptional(false);
        buildMemberExpression();
        assertToCodeIs("x[index]");
    }

    @Test
    void testToCodeWithPropertyValueAndComputed() {
        withObject(new Identifier("x"));
        withType("string");
        withValue(2);
        withComputed(true);
        withOptional(false);
        buildMemberExpression();
        assertToCodeIs("x[2]");
    }

    @Test
    void testIsOptional() {
        withObject(new Identifier("x"));
        withType("string");
        withValue(2);
        withComputed(true);
        withOptional(true);
        buildMemberExpression();
        assertIsOptional();
    }

    @Test
    void testIsNotOptional() {
        withObject(new Identifier("x"));
        withType("string");
        withValue(2);
        withComputed(true);
        withOptional(false);
        buildMemberExpression();
        assertIsNotOptional();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withObject(Expression expression) {
        object = expression;
    }

    private void withType(String type) {
        this.type = type;
    }

    private void withName(String name) {
        this.name = name;
    }

    private void withValue(int value) {
        this.value = value;
    }

    private void withComputed(boolean value) {
        computed = value;
    }

    private void withOptional(boolean value) {
        optional = value;
    }

    private void buildMemberExpression() {
        memberExpression = new MemberExpression(
            object,
            type, 
            name, 
            value,
            computed,
            optional
        );
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, memberExpression.toCode());
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

    private void assertIsOptional() {
        Assertions.assertTrue(memberExpression.isOptional());
    }

    private void assertIsNotOptional() {
        Assertions.assertFalse(memberExpression.isOptional());
    }
}

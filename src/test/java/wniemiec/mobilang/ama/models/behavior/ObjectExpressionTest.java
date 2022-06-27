package wniemiec.mobilang.ama.models.behavior;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ObjectExpressionTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ObjectExpression objectExpression;
    private Map<String, Expression> properties;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        objectExpression = null;
        properties = new LinkedHashMap<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithoutProperties() {
        buildObjectExpression();
        assertToCodeIs("{ }");
    }

    @Test
    void testToCodeWithSingleProperty() {
        withProperty("color", Literal.ofString("red"));
        buildObjectExpression();
        assertToCodeIs("{ color: \"red\" }");
    }

    @Test
    void testToCodeWithMultipleProperties() {
        withProperty("color", Literal.ofString("red"));
        withProperty("background-color", Literal.ofString("purple"));
        buildObjectExpression();
        assertToCodeIs("{ color: \"red\", background-color: \"purple\" }");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withProperty(String key, Expression expression) {
        properties.put(key, expression);
    }

    private void buildObjectExpression() {
        objectExpression = new ObjectExpression(properties);
    }

    private void assertToCodeIs(String expectedCode) {
        assertHasSameLine(expectedCode, objectExpression.toCode());
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

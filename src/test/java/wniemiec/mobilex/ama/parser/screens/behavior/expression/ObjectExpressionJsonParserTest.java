package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class ObjectExpressionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ObjectExpressionJsonParser parser;
    private Expression parsedExpression;
    private JSONArray properties;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = ObjectExpressionJsonParser.getInstance();
        parsedExpression = null;
        properties = new JSONArray();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithNamedKeyAndValue() throws ParseException, IOException {
        withProperty(buildNamedKey("id"), buildLiteral("$AB12345"));
        doParsing();
        assertParsedCodeIs("{ id: \"$AB12345\" }");
    }

    @Test
    void testParseWithValueKeyAndValue() throws ParseException, IOException {
        withProperty(buildValuedKey("$id"), buildLiteral("$AB12345"));
        doParsing();
        assertParsedCodeIs("{ '$id': \"$AB12345\" }");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private JSONObject buildNamedKey(String value) {
        JSONObject key = new JSONObject();

        key.put("name", value);

        return key;
    }

    private JSONObject buildLiteral(Object value) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Literal");
        expression.put("value", value);

        return expression;
    }

    private void withProperty(JSONObject key, JSONObject value) {
        properties.put(buildProperty(key, value));
    }

    private JSONObject buildProperty(JSONObject key, JSONObject value) {
        JSONObject property = new JSONObject();

        property.put("key", key);
        property.put("value", value);

        return property;
    }

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildObjectExpression());
    }

    private JSONObject buildObjectExpression() {
        JSONObject expression = new JSONObject();

        expression.put("type", "ObjectExpression");
        expression.put("properties", properties);

        return expression;
    }

    private void assertParsedCodeIs(String code) {
        assertHasSameLine(code, parsedExpression.toCode());
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

    private JSONObject buildValuedKey(String value) {
        JSONObject key = new JSONObject();

        key.put("value", value);

        return key;
    }
}

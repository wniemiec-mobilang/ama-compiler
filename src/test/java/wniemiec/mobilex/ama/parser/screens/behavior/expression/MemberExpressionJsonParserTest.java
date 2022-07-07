package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import java.io.IOException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class MemberExpressionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private MemberExpressionJsonParser parser;
    private Expression parsedExpression;
    private JSONObject object;
    private JSONObject property;
    private Boolean computed;
    private Boolean optional;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = MemberExpressionJsonParser.getInstance();
        parsedExpression = null;
        object = null;
        property = null;
        computed = null;
        optional = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithValuedProperty() throws ParseException, IOException {
        withObject(buildIdentifier("nameList"));
        withValuedProperty(0);
        withComputed(true);
        withOptional(false);
        doParsing();
        assertParsedCodeIs("nameList[0]");
    }

    @Test
    void testParseWithNonComputedNamedProperty() throws ParseException, IOException {
        withObject(buildIdentifier("nameList"));
        withNamedProperty("length");
        withComputed(false);
        withOptional(false);
        doParsing();
        assertParsedCodeIs("nameList.length");
    }

    @Test
    void testParseWithComputedNamedProperty() throws ParseException, IOException {
        withObject(buildIdentifier("nameList"));
        withNamedProperty("index + 1");
        withComputed(true);
        withOptional(false);
        doParsing();
        assertParsedCodeIs("nameList[index + 1]");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private JSONObject buildIdentifier(String name) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Identifier");
        expression.put("name", name);

        return expression;
    }

    private void withObject(JSONObject object) {
        this.object = object;
    }

    private void withValuedProperty(Integer value) {
        property = new JSONObject();

        property.put("type", "number");
        property.put("value", value);
    }

    private void withComputed(Boolean value) {
        computed = value;
    }

    private void withOptional(Boolean value) {
        optional = value;
    }

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildMemberExpression());
    }

    private JSONObject buildMemberExpression() {
        JSONObject expression = new JSONObject();

        expression.put("type", "MemberExpression");
        expression.put("object", object);
        expression.put("property", property);
        expression.put("computed", computed);
        expression.put("optional", optional);

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

    private void withNamedProperty(String name) {
        property = new JSONObject();

        property.put("type", "string");
        property.put("name", name);
    }
}

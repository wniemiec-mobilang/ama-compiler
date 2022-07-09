package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class ArrayExpressionJsonParserTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ArrayExpressionJsonParser parser;
    private Expression parsedExpression;
    private JSONArray elements;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = ArrayExpressionJsonParser.getInstance();
        parsedExpression = null;
        elements = new JSONArray();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithMultipleElements() throws ParseException, IOException {
        withElement(buildIdentifier("n1"));
        withElement(buildIdentifier("n2"));
        withElement(buildIdentifier("n3"));
        doParsing();
        assertParsedCodeIs("[n1, n2, n3]");
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

    private void withElement(JSONObject element) {
        elements.put(element);
    }

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildArrayExpression());
    }

    private JSONObject buildArrayExpression() {
        JSONObject expression = new JSONObject();

        expression.put("type", "ArrayExpression");
        expression.put("elements", elements);

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
}

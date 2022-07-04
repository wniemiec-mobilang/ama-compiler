package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class TemplateLiteralExpressionJsonParserTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private TemplateLiteralExpressionJsonParser parser;
    private Expression parsedExpression;
    private JSONArray expressions;
    private JSONArray quasis;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = TemplateLiteralExpressionJsonParser.getInstance();
        parsedExpression = null;
        expressions = new JSONArray();
        quasis = new JSONArray();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithArgumentsAndQuasis() 
    throws ParseException, IOException {
        withExpression(buildIdentifier("i"));
        withQuasis(buildTemplateElement("index: "));
        doParsing();
        assertParsedCodeIs("`index: ${i}`");
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

    private void withExpression(JSONObject expression) {
        expressions.put(expression);
    }

    private JSONObject buildTemplateElement(Object value) {
        JSONObject expression = new JSONObject();
        JSONObject jsonValue = new JSONObject();

        jsonValue.put("raw", value);
        expression.put("type", "TemplateElement");
        expression.put("value", jsonValue);
        expression.put("tail", false);

        return expression;
    }

    private void withQuasis(JSONObject expression) {
        quasis.put(expression);
    }

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildTemplateLiteralExpression());
    }

    private JSONObject buildTemplateLiteralExpression() {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "TemplateLiteral");
        instruction.put("expressions", expressions);
        instruction.put("quasis", quasis);

        return instruction;
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

package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class NewExpressionJsonParserTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private NewExpressionJsonParser parser;
    private Expression parsedExpression;
    private JSONObject callee;
    private JSONArray arguments;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = NewExpressionJsonParser.getInstance();
        parsedExpression = null;
        callee = null;
        arguments = new JSONArray();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithCalleeAndOneArgument() throws ParseException, IOException {
        withCallee(buildIdentifier("Integer"));
        withArgument(buildLiteral(2));
        doParsing();
        assertParsedCodeIs("new Integer(2)");
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

    private void withCallee(JSONObject callee) {
        this.callee = callee;
    }

    private JSONObject buildLiteral(Object value) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Literal");
        expression.put("value", value);

        return expression;
    }

    private void withArgument(JSONObject expression) {
        if (expression == null) {
            arguments = null;
        }
        else {
            arguments.put(expression);
        }
    }

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildNewExpression());
    }

    private JSONObject buildNewExpression() {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "NewExpression");
        instruction.put("callee", callee);
        instruction.put("arguments", arguments);

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

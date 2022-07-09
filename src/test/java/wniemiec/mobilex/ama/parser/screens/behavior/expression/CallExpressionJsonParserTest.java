package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import java.io.IOException;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class CallExpressionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private CallExpressionJsonParser parser;
    private Expression parsedExpression;
    private JSONArray arguments;
    private JSONObject callee;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = CallExpressionJsonParser.getInstance();
        parsedExpression = null;
        arguments = new JSONArray();
        callee = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithCalleeAndMultipleArguments() throws ParseException, IOException {
        withCallee(buildIdentifier("sum"));
        withArguments(
            buildIdentifier("n1"), 
            buildIdentifier("n2")
        );
        doParsing();
        assertParsedCodeIs("sum(n1, n2)");
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

    private void withCallee(JSONObject instruction) {
        callee = instruction;
    }

    private void withArguments(JSONObject... parameters) {
        Arrays.stream(parameters).forEach(arguments::put);
    }    

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildCallExpression());
    }

    private JSONObject buildCallExpression() {
        JSONObject expression = new JSONObject();

        expression.put("type", "CallExpression");
        expression.put("callee", callee);
        expression.put("arguments", arguments);

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

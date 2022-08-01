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


class ArrowFunctionExpressionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ArrowFunctionExpressionJsonParser parser;
    private Expression parsedExpression;
    private Boolean async;
    private JSONArray params;
    private JSONObject body;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = ArrowFunctionExpressionJsonParser.getInstance();
        async = null;
        parsedExpression = null;
        params = new JSONArray();
        body = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithAsyncAndParamsAndBody() throws ParseException, IOException {
        withAsync(true);
        withParams(
            buildIdentifier("n1"), 
            buildIdentifier("n2")
        );
        withBody(buildIdentifier("n1"));
        doParsing();
        assertParsedCodeIs("async (n1, n2) => n1;");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withAsync(Boolean value) {
        async = value;
    }

    private JSONObject buildIdentifier(String name) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Identifier");
        expression.put("name", name);

        return expression;
    }

    private void withParams(JSONObject... parameters) {
        Arrays.stream(parameters).forEach(params::put);
    }

    private void withBody(JSONObject instruction) {
        body = instruction;
    }

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildArrowFunctionExpression());
    }

    private JSONObject buildArrowFunctionExpression() {
        JSONObject expression = new JSONObject();

        expression.put("type", "ArrowFunctionDeclaration");
        expression.put("async", async);
        expression.put("params", params);
        expression.put("body", body);

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

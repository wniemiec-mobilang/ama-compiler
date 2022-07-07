package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import java.io.IOException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class LogicalExpressionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private LogicalExpressionJsonParser parser;
    private Expression parsedExpression;
    private JSONObject left;
    private JSONObject right;
    private String operator;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = LogicalExpressionJsonParser.getInstance();
        parsedExpression = null;
        left = null;
        right = null;
        operator = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithOperatorAndLeftAndRight() throws ParseException, IOException {
        withOperator("=");
        withLeft(buildIdentifier("sum"));
        withRight(buildLiteral(0.0));
        doParsing();
        assertParsedCodeIs("sum = 0.0");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withOperator(String symbol) {
        operator = symbol;
    }

    private JSONObject buildIdentifier(String name) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Identifier");
        expression.put("name", name);

        return expression;
    }

    private void withLeft(JSONObject expression) {
        left = expression;
    }

    private JSONObject buildLiteral(Object value) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Literal");
        expression.put("value", value);

        return expression;
    }

    private void withRight(JSONObject expression) {
        right = expression;
    }

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildLogicalExpression());
    }

    private JSONObject buildLogicalExpression() {
        JSONObject expression = new JSONObject();

        expression.put("type", "LogicalExpression");
        expression.put("operator", operator);
        expression.put("left", left);
        expression.put("right", right);

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

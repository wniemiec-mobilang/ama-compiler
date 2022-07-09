package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import java.io.IOException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class BinaryExpressionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private BinaryExpressionJsonParser parser;
    private Expression parsedExpression;
    private String operator;
    private JSONObject left;
    private JSONObject right;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = BinaryExpressionJsonParser.getInstance();
        parsedExpression = null;
        operator = null;
        left = null;
        right = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithOperatorAndLeftAndRight() throws ParseException, IOException {
        withOperator("+");
        withLeft(buildIdentifier("n1"));
        withRight(buildIdentifier("n2"));
        doParsing();
        assertParsedCodeIs("n1 + n2");
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

    private void withOperator(String symbol) {
        operator = symbol;
    }

    private void withLeft(JSONObject expression) {
        left = expression;
    }

    private void withRight(JSONObject expression) {
        right = expression;
    }

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildBinaryExpression());
    }

    private JSONObject buildBinaryExpression() {
        JSONObject expression = new JSONObject();

        expression.put("type", "BinaryExpression");
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

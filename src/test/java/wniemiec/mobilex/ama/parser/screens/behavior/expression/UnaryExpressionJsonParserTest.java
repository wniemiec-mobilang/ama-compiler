package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import java.io.IOException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class UnaryExpressionJsonParserTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private UnaryExpressionJsonParser parser;
    private Expression parsedExpression;
    private String operator;
    private boolean prefix;
    private JSONObject argument;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = UnaryExpressionJsonParser.getInstance();
        parsedExpression = null;
        operator = null;
        prefix = false;
        argument = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testToCodeWithOperatorAndPrefixAndArgument() throws ParseException, IOException {
        withOperator("!");
        withPrefix(true);
        withArgument(buildIdentifier("isEmpty"));
        doParsing();
        assertParsedCodeIs("!isEmpty");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withOperator(String operator) {
        this.operator = operator;
    }

    private void withPrefix(boolean value) {
        prefix = value;
    }

    private JSONObject buildIdentifier(String name) {
        JSONObject expression = new JSONObject();

        expression.put("type", "Identifier");
        expression.put("name", name);

        return expression;
    }

    private void withArgument(JSONObject expression) {
        argument = expression;
    }

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildPostIncrementExpression());
    }

    private JSONObject buildPostIncrementExpression() {
        JSONObject instruction = new JSONObject();

        instruction.put("type", "UpdateExpression");
        instruction.put("operator", operator);
        instruction.put("prefix", prefix);
        instruction.put("argument", argument);

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

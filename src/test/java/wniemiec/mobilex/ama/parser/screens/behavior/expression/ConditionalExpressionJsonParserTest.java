package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import java.io.IOException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class ConditionalExpressionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ConditionalExpressionJsonParser parser;
    private Expression parsedExpression;
    private JSONObject test;
    private JSONObject consequent;
    private JSONObject alternate;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = ConditionalExpressionJsonParser.getInstance();
        parsedExpression = null;
        test = null;
        consequent = null;
        alternate = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithTestAndConsequentAndAlternate() throws ParseException, IOException {
        withTest(buildIdentifier("hasValue"));
        withConsequent(buildIdentifier("n1"));
        withAlternate(buildIdentifier("n2"));
        doParsing();
        assertParsedCodeIs("hasValue ? n1 : n2");
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

    private void withTest(JSONObject expression) {
        test = expression;
    }

    private void withConsequent(JSONObject expression) {
        consequent = expression;
    }

    private void withAlternate(JSONObject expression) {
        alternate = expression;
    }

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildConditionalExpression());
    }

    private JSONObject buildConditionalExpression() {
        JSONObject expression = new JSONObject();

        expression.put("type", "ConditionalExpression");
        expression.put("test", test);
        expression.put("consequent", consequent);
        expression.put("alternate", alternate);

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

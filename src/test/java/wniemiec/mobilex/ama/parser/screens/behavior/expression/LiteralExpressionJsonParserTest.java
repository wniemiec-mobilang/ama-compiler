package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import java.io.IOException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class LiteralExpressionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private LiteralExpressionJsonParser parser;
    private Expression parsedExpression;
    private Object value;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = LiteralExpressionJsonParser.getInstance();
        value = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithStringLiteral() throws ParseException, IOException {
        withValue("sum");
        doParsing();
        assertParsedCodeIs("\"sum\"");
    }

    @Test
    void testParseWithIntegerLiteral() throws ParseException, IOException {
        withValue(0);
        doParsing();
        assertParsedCodeIs("0");
    }

    @Test
    void testParseWithFloatLiteral() throws ParseException, IOException {
        withValue(0.0f);
        doParsing();
        assertParsedCodeIs("0.0");
    }

    @Test
    void testParseWithDoubleLiteral() throws ParseException, IOException {
        withValue(0.0);
        doParsing();
        assertParsedCodeIs("0.0");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withValue(Object literal) {
        value = literal;
    }

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildLiteralExpression());
    }

    private JSONObject buildLiteralExpression() {
        JSONObject expression = new JSONObject();

        expression.put("type", "LiteralExpression");
        expression.put("value", value);

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

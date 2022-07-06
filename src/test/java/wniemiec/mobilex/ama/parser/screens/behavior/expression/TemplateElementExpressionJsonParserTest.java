package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import java.io.IOException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class TemplateElementExpressionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private TemplateElementExpressionJsonParser parser;
    private Expression parsedExpression;
    private Object value;
    private boolean tail;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = TemplateElementExpressionJsonParser.getInstance();
        parsedExpression = null;
        value = null;
        tail = false;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithValueAndTail() throws ParseException, IOException {
        withTail(true);
        withValue("hello world");
        doParsing();
        assertParsedCodeIs("hello world");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withTail(boolean value) {
        tail = value;
    }

    private void withValue(Object value) {
        this.value = value;
    }

    private void doParsing() throws ParseException, IOException {
        parsedExpression = parser.parse(buildTemplateElement());
    }

    private JSONObject buildTemplateElement() {
        JSONObject expression = new JSONObject();
        JSONObject jsonValue = new JSONObject();

        jsonValue.put("raw", value);
        expression.put("type", "TemplateElement");
        expression.put("value", jsonValue);
        expression.put("tail", tail);

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

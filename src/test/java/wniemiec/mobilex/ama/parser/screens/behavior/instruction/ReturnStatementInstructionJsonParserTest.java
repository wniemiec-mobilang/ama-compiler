package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import java.io.IOException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class ReturnStatementInstructionJsonParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private ReturnStatementInstructionJsonParser parser;
    private Instruction parsedInstruction;
    private JSONObject returnArgument;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = ReturnStatementInstructionJsonParser.getInstance();
        parsedInstruction = null;
        returnArgument = new JSONObject();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithArgument() throws ParseException, IOException {
        withArgument(buildIdentifier("sum"));
        doParsing();
        assertParsedCodeIs("return sum");
    }

    @Test
    void testParseWithoutArgument() throws ParseException, IOException {
        doParsing();
        assertParsedCodeIs("return");
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

    private void withArgument(JSONObject argument) {
        returnArgument.put("argument", argument);
    }

    private void doParsing() throws ParseException, IOException {
        parsedInstruction = parser.parse(returnArgument);
    }

    private void assertParsedCodeIs(String code) {
        assertHasSameLine(code, parsedInstruction.toCode());
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

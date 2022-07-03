package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import java.io.IOException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class IfStatementInstructionJsonParserTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private IfStatementInstructionJsonParser parser;
    private Instruction parsedInstruction;
    private JSONObject test;
    private JSONObject consequent;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = IfStatementInstructionJsonParser.getInstance();
        parsedInstruction = null;
        test = new JSONObject();
        consequent = new JSONObject();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithArgument() throws ParseException, IOException {
        withTest(buildIdentifier("hasValue"));
        withConsequent(buildReturnInstruction());
        doParsing();
        assertParsedCodeIs("if (hasValue) return");
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

    private JSONObject buildReturnInstruction() {
        JSONObject expression = new JSONObject();

        expression.put("type", "ReturnStatement");

        return expression;
    }

    private void withConsequent(JSONObject instruction) {
        consequent = instruction;
    }

    private void doParsing() throws ParseException, IOException {
        parsedInstruction = parser.parse(buildIfStatement());
    }

    private JSONObject buildIfStatement() {
        JSONObject expression = new JSONObject();

        expression.put("type", "IfStatement");
        expression.put("test", test);
        expression.put("consequent", consequent);

        return expression;
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
